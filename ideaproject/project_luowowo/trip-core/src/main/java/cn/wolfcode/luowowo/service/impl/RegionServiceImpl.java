package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.Region;
import cn.wolfcode.luowowo.query.QueryObject;
import cn.wolfcode.luowowo.query.RegionQuery;
import cn.wolfcode.luowowo.repository.RegionRepository;
import cn.wolfcode.luowowo.service.IDestinationService;
import cn.wolfcode.luowowo.service.IRegionService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class RegionServiceImpl implements IRegionService {
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private MongoTemplate template;
    @Autowired
    private IDestinationService destinationService;

    @Override
    public void save(Region region) {
        region.setId(null);
        regionRepository.save(region);
    }

    @Override
    public void delete(String id) {
        regionRepository.deleteById(id);
    }

    @Override
    public void update(Region region) {
        regionRepository.save(region);
    }

    @Override
    public Region get(String id) {
        Optional<Region> region = regionRepository.findById(id);
        if (region.isPresent()){
            return region.get();
        }
        return null;
    }

    @Override
    public List<Region> listAll() {
        return regionRepository.findAll();
    }

    @Override
    public Page query(RegionQuery qo) {
        //在mongodb中没有针对高级查询的分页操作,需要自己定义
        //自己封装分页对象需要注意的2个核心
        //分页条件对象
        Query query = new Query();
        //总记录数
        long count = template.count(query, Region.class);
        if (count == 0){
            return Page.empty();
        }
        //每页显示记录
        Pageable pageable = PageRequest.of(qo.getCurrentPage()-1,qo.getPageSize());
        query.with(pageable);
        List<Region> regionList = template.find(query, Region.class);
        return new PageImpl(regionList,pageable,count);
    }

    @Override
    public void saveOrUpdate(Region region) {
        if (!StringUtils.hasLength(region.getId())){
            this.save(region);
        }else{
            //this.update(region);

            //部分字段更新
            //Query query = new Query();
            //query.addCriteria(Criteria.where("_id").is(region.getId()));
            //Update update = new Update();
            //update.set("name",region.getName());
            //template.updateMulti(query,update,Region.class);
        }

    }

    @Override
    public void changeHotValue(int hot, String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("ishot",hot);
        template.updateMulti(query,update,Region.class);
    }

    @Override
    public List<Destination> getDestByRegionId(String rid) {
        //获取区域关联的目的地
        Region region = this.get(rid);
        List<String> refIds = region.getRefIds();
        //通过refId这些id查询目的地对象集合
        List<Destination> destinations = destinationService.queryByIds(refIds);
        return destinations;
    }

    @Override
    public List<Region> queryHotRegions() {
        return regionRepository.findByIshotOrderBySequenceAsc(Region.STATE_HOT);
    }

}
