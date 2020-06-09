package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.Region;
import cn.wolfcode.luowowo.query.RegionQuery;
import cn.wolfcode.luowowo.repository.RegionRepository;
import cn.wolfcode.luowowo.service.IRegionService;
import cn.wolfcode.luowowo.util.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
//@Transactional  暂时先别管事务
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private RegionRepository repository;

    @Autowired
    private MongoTemplate template;


    public Region get(String id) {
        Optional<Region> optional = repository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    //page对象里面包括: 总页数 每页显示条数......
    public Page<Region> query(RegionQuery qo) {
        //PageResult 7核心属性
        //核心: count  list
        //使用template方式只能返回list, 需要将list分装成pagte对象

        //1: 查询总数:total
        Query query = new Query();
        long total = template.count(query, Region.class);
        if(total == 0){
            return Page.empty();  //空的分页对象
        }
        //2: 每页显示条数集合: list
        //设置页面显示条数, 还有当前页
        PageRequest pageable = PageRequest.of(qo.getCurrentPage() - 1, qo.getPageSize(),
                Sort.Direction.DESC, "_id");
        query.with(pageable);
        List<Region> list = template.find(query, Region.class);

        //3:封装page对象
        return new PageImpl<Region>(list,pageable,total);
    }

    public void saveOrUpdate(Region region) {
        if(!StringUtils.hasLength(region.getId())){
            //添加
            region.setId(null);
            repository.save(region);
        }else{
            //更新
            //方案1: 1:查询  2: 替代  3:save
            //方案2: 显示使用update逻辑 update set...
            //条件
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(region.getId()));
           /*
            //更新的列
            Update update = new Update();
            update.set("name",region.getName());
            update.set("sn",region.getSn());
            update.set("refIds",region.getRefIds());
            update.set("ishot",region.getIshot());
            update.set("sequence",region.getSequence());
            update.set("info",region.getInfo());
            //执行更新
            template.updateMulti(query, update, Region.class);*/
            DBHelper.update(template, query, region, "name","sn","refIds","ishot","sequence","info");

        }
    }

    public void changeHotValue(String id, int hot) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("ishot",hot);
        template.updateMulti(query, update, Region.class);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public List<Region> queryHotRegion() {
        //热门属性与序号排序
        return repository.findByIshotOrderBySequenceAsc(Region.STATE_HOT);
    }
}
