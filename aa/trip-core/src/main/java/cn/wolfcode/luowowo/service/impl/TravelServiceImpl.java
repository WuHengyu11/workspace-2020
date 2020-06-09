package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.Travel;
import cn.wolfcode.luowowo.exception.LogicException;
import cn.wolfcode.luowowo.query.TravelCondition;
import cn.wolfcode.luowowo.query.TravelQuery;
import cn.wolfcode.luowowo.repository.TravelRepository;
import cn.wolfcode.luowowo.service.IDestinationService;
import cn.wolfcode.luowowo.service.ITravelService;
import cn.wolfcode.luowowo.service.IUserInfoService;
import cn.wolfcode.luowowo.util.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional  暂时先别管事务
public class TravelServiceImpl implements ITravelService {

    @Autowired
    private TravelRepository repository;

    @Autowired
    private IDestinationService destinationService;

    @Autowired
    private MongoTemplate template;

    @Autowired
    private IUserInfoService userInfoService;


    public Travel get(String id) {
        Optional<Travel> optional = repository.findById(id);
        if(optional.isPresent()){
            Travel travel = optional.get();
            //用户信息
            travel.setAuthor(userInfoService.get(travel.getUserId()));

            //目的地信息
            Destination dest = destinationService.get(travel.getDestId());
            travel.setDest(dest);
            return travel;
        }
        return null;
    }

    //page对象里面包括: 总页数 每页显示条数......
    public Page<Travel> query(TravelQuery qo) {



        Query query = new Query();

        if(StringUtils.hasLength(qo.getDestId())){
            query.addCriteria(Criteria.where("destId").is(qo.getDestId()));
        }

        TravelCondition day = qo.getDay();
        TravelCondition perExpend = qo.getPerExpend();
        //旅游天数
        if(day != null){
            query.addCriteria(Criteria.where("day").gte(day.getMin()).lte(day.getMax()));
        }
        //旅游人均消费
        if(perExpend != null){
            query.addCriteria(Criteria.where("perExpend").gte(perExpend.getMin()).lte(perExpend.getMax()));
        }

        //1: 默认, 按照最新(时间排序)  2:最热-viewnum
        if(qo.getOrderType() == 2){
            qo.setPageable(PageRequest.of(qo.getCurrentPage() - 1, qo.getPageSize(),
                    Sort.Direction.DESC, "viewnum"));
        }else {
            qo.setPageable(PageRequest.of(qo.getCurrentPage() - 1, qo.getPageSize(),
                    Sort.Direction.DESC, "createTime"));
        }
        Page<Travel> page = DBHelper.query(template, Travel.class, query, qo.getPageable());
        //缺少用户对象
        for (Travel travel : page.getContent()) {
            travel.setAuthor(userInfoService.get(travel.getUserId()));
        }

        return page;
    }

    public String saveOrUpdate(Travel travel) {

        //添加或更新时, 需要准备的内容

        //1: 目的地名称
        Destination dest = destinationService.get(travel.getDestId());
        travel.setDestName(dest.getName());

        //3:创建时间/最后更新时间
        travel.setLastUpdateTime(new Date());
        if(!StringUtils.hasLength(travel.getId())){
            //添加
            travel.setId(null);
            //创建时间
            travel.setCreateTime(new Date());

            repository.save(travel);
        }else{
            //更新
            //方案1: 1:查询  2: 替代  3:save
            //方案2: 显示使用update逻辑 update set...
            //条件
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(travel.getId()));

            DBHelper.update(template, query, travel,
                    "destId","destName","title",
                    "coverUrl","travelTime","perExpend",
                    "day","person","lastUpdateTime",
                    "isPublic","state","summary","content");
        }

        return travel.getId();
    }


    public void delete(String id) {
        repository.deleteById(id);
    }


    public void changeState(String id, int state) {
        //1:满足什么条件才进行审核
        Optional<Travel> op = repository.findById(id);
        if(!op.isPresent() && state != Travel.STATE_WAITING){
            throw new LogicException("参数异常");
        }
        Travel travel = new Travel();
        travel.setId(id);
        travel.setLastUpdateTime(new Date());
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        if(state == Travel.STATE_RELEASE){
            //2:审核通过要做什么
            //发布时间  最后更新时间  state
            travel.setState(Travel.STATE_RELEASE);  //通过
            travel.setReleaseTime(new Date());
        }else{
            //3:审核拒绝要做什么
            //发布时间  最后更新时间  state
            travel.setState(Travel.STATE_REJECT);  //拒绝
            travel.setReleaseTime(null);
        }
        DBHelper.update(template, query, travel, "state","lastUpdateTime","releaseTime");

    }

    public List<Travel> getViewnumTop3(String destId) {
        //点击量前3
        Pageable of = PageRequest.of(0, 3, Sort.Direction.DESC, "viewnum");
        return repository.findByDestId(destId, of);
    }

    @Override
    public List<Travel> list() {
        return repository.findAll();
    }

    @Override
    public List<Travel> findByDestName(String destName) {
        List<Travel> list = repository.findByDestName(destName);
        for (Travel travel : list) {
            //用户信息
            travel.setAuthor(userInfoService.get(travel.getUserId()));
        }
        return list;
    }
}
