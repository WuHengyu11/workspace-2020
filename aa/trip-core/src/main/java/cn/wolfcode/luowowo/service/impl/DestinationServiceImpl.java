package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.Region;
import cn.wolfcode.luowowo.query.DestinationQuery;
import cn.wolfcode.luowowo.repository.DestinationRepository;
import cn.wolfcode.luowowo.service.IDestinationService;
import cn.wolfcode.luowowo.service.IRegionService;
import cn.wolfcode.luowowo.util.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional  暂时先别管事务
public class DestinationServiceImpl implements IDestinationService {

    @Autowired
    private DestinationRepository repository;

    @Autowired
    private MongoTemplate template;

    @Autowired
    private IRegionService regionService;


    public Destination get(String id) {
        Optional<Destination> optional = repository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    //page对象里面包括: 总页数 每页显示条数......
    public Page<Destination> query(DestinationQuery qo) {
        Query query = new Query();

        if(StringUtils.hasLength(qo.getParentId())){
            query.addCriteria(Criteria.where("parentId").is(qo.getParentId()));
        }else{
            query.addCriteria(Criteria.where("parentId").is(null));
        }


        if(StringUtils.hasLength(qo.getKeyword())){
           query.addCriteria(Criteria.where("name").regex(".*"+qo.getKeyword()+".*"));
        }

        return DBHelper.query(template, Destination.class, query, qo.getPageable());

    }

    public List<Destination> list() {
        return repository.findAll();
    }

    public List<Destination> getDestByRegionId(String rid) {

        //查询挂载的目的地id集合
        Region region = regionService.get(rid);
        List<String> refIds = region.getRefIds();
        //select from dest where id in (refIds)
        return repository.findByIdIn(refIds);
    }


    public List<Destination> getDestByRegionIdForApi(String regionId) {
        //第一层:关联的目的地
        //区分:如果regionId= -1 , 表示查询所有的中国省份
        List<Destination> list = null;
        if("-1".equals(regionId)){
            //中国所有的省份
            list = repository.findByParentName("中国");
        }else{
            //查询挂载的目的地id集合
            Region region = regionService.get(regionId);
            List<String> refIds = region.getRefIds();
            list = repository.findByIdIn(refIds);
        }
        //第二层: 查询关联目的地下的子目的地
        //查询每个目的地关联子目的地, 以前mybatis可以通过resultMap配置, 现在mongodb没这个功能
        //只能直接去关联查询

        for (Destination dest : list) {
            //以父目的地name作为条件查询儿子
            //bug: 数据没有parentName属性, 要改动用parentId方式
            //jpa
            PageRequest of = PageRequest.of(0, 5);
            List<Destination> children = repository.findByParentId(dest.getId(), of);
            dest.setChildren(children);
        }
        return list;
    }

    //根>>中国>>广东>>广州:    parentId:广州id
    public List<Destination> getToasts(String parentId) {
        if(!StringUtils.hasLength(parentId)){
            return Collections.emptyList();
        }
        List<Destination> list = new ArrayList<>();
        //查询当前目的地
        createToast(list, parentId);
        Collections.reverse(list);  //取反
        return list;
    }
    //创建toast
    private void createToast(List<Destination> list, String parentId) {
        Optional<Destination> op = repository.findById(parentId);  //广州
        if(!op.isPresent()){
            return;
        }
        list.add(op.get());
        if(StringUtils.hasLength(op.get().getParentId())){
            createToast(list, op.get().getParentId());
        }
    }

    @Override
    public Destination getByName(String keyword) {
        return repository.findByName(keyword);
    }
}
