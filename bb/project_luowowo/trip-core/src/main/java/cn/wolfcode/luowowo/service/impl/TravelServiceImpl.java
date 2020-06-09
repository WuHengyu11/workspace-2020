package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.Travel;
import cn.wolfcode.luowowo.domain.UserInfo;
import cn.wolfcode.luowowo.query.TravelCondition;
import cn.wolfcode.luowowo.query.TravelQuery;
import cn.wolfcode.luowowo.redis.IUserInfoRedisService;
import cn.wolfcode.luowowo.repository.TravelRepository;
import cn.wolfcode.luowowo.service.IDestinationService;
import cn.wolfcode.luowowo.service.ITravelService;
import cn.wolfcode.luowowo.service.IUserInfoService;
import cn.wolfcode.luowowo.util.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TravelServiceImpl implements ITravelService {
    @Autowired
    private TravelRepository travelRepository;
    @Autowired
    private MongoTemplate template;
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IDestinationService destinationService;

    @Override
    public void save(Travel travel) {
        travel.setId(null);
        travelRepository.save(travel);
    }

    @Override
    public void delete(String id) {
        travelRepository.deleteById(id);
    }

    @Override
    public void update(Travel travel) {
        travelRepository.save(travel);
    }

    @Override
    public Travel get(String id) {
        Optional<Travel> op = travelRepository.findById(id);
        if (op.isPresent()){
            //用户信息
            Travel travel = op.get();
            travel.setAuthor(userInfoService.get(travel.getUserId()));
            //目的地信息
            Destination dest = destinationService.get(travel.getDestId());
            travel.setDest(dest);
            return travel;
        }
        return null;
     }

    @Override
    public List<Travel> listAll() {
        return travelRepository.findAll();
    }

    @Override
    public Page<Travel> query(TravelQuery qo) {
        //分页条件对象
        Query query = new Query();

        //条件查询
        if (StringUtils.hasLength(qo.getDestId())){
            query.addCriteria(Criteria.where("destId").is(qo.getDestId()));
        }

        //天数范围查询
        TravelCondition day = qo.getDay();
        if (day != null){
            query.addCriteria(Criteria.where("day").gte(day.getMin()).lte(day.getMax()));
        }
        TravelCondition perExpend = qo.getPerExpend();
        if (perExpend != null){
            query.addCriteria(Criteria.where("perExpend").gte(perExpend.getMin()).lte(perExpend.getMax()));
        }
        //排序处理
        Pageable pageable = PageRequest.of(qo.getCurrentPage() - 1, qo.getPageSize(), Sort.Direction.DESC,qo.getOrderBy());

        //处理用户对象
        Page<Travel> page = DBHelper.query(template, Travel.class, query, pageable);
        for (Travel travel : page) {
            UserInfo userInfo = userInfoService.get(travel.getUserId());
            travel.setAuthor(userInfo);
        }
        return page;
    }

    @Override
    public void saveOrUpdate(Travel travel) {
        //目的地名称
        travel.setDestName(destinationService.get(travel.getDestId()).getName());
        //发布时间--只有审核通过后才有值
        //最后更新时间
        travel.setLastUpdateTime(new Date());
        if (!StringUtils.hasLength(travel.getId())){
            //创建时间
            travel.setCreateTime(new Date());
            this.save(travel);
        }else{
            //this.update(travel);
        }

    }

    @Override
    public List<Travel> queryViewnumTop3(String destId) {
        Pageable pageable = PageRequest.of(0,3,Sort.Direction.DESC,"viewnum");
        return travelRepository.findByDestId(destId,pageable);
    }

    @Override
    public void changeState(Travel travel) {
        //最后更新时间处理
        travel.setLastUpdateTime(new Date());
        //发布处理
        if (travel.getState() == Travel.STATE_RELEASE){
            travel.setReleaseTime(new Date());
        }else {
            travel.setReleaseTime(null);
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(travel.getId()));
        DBHelper.update(template,query,travel,"state","lastUpdateTime","releaseTime");
    }

    @Override
    public String getContentById(String id) {
        Optional<Travel> op = travelRepository.findById(id);
        if (op.isPresent()){
            return op.get().getContent();
        }
        return "";
    }
}
