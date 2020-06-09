package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.domain.StrategyCatalog;
import cn.wolfcode.luowowo.query.StrategyCatalogQuery;
import cn.wolfcode.luowowo.repository.StrategyCatalogRepository;
import cn.wolfcode.luowowo.service.IStrategyCatalogService;
import cn.wolfcode.luowowo.service.IStrategyService;
import cn.wolfcode.luowowo.util.DBHelper;
import cn.wolfcode.luowowo.vo.CatalogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
//@Transactional  暂时先别管事务
public class StrategyCatalogServiceImpl implements IStrategyCatalogService {

    @Autowired
    private StrategyCatalogRepository repository;

    @Autowired
    private MongoTemplate template;

    @Autowired
    private IStrategyService strategyService;


    public StrategyCatalog get(String id) {
        Optional<StrategyCatalog> optional = repository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    //page对象里面包括: 总页数 每页显示条数......
    public Page<StrategyCatalog> query(StrategyCatalogQuery qo) {
        Query query = new Query();

        return DBHelper.query(template, StrategyCatalog.class, query, qo.getPageable());
    }

    public void saveOrUpdate(StrategyCatalog region) {
        if(!StringUtils.hasLength(region.getId())){
            //添加
            region.setId(null);
            repository.save(region);
        }else{

            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(region.getId()));


            //DBHelper.update(template, query, region, "name","sn","refIds","ishot","sequence","info");

        }
    }



    public void delete(String id) {
        repository.deleteById(id);
    }

    public List<CatalogVO> groupList() {
        List<CatalogVO> list = new ArrayList<>();
        TypedAggregation<StrategyCatalog> agg = Aggregation.newAggregation(StrategyCatalog.class,
                Aggregation.group("destName").
                        push("name").as("names").
                        push("id").as("ids")
        );
        AggregationResults<Map> result = template.aggregate(agg,Map.class);
        List<Map> datas = result.getMappedResults();
        for (Map data : datas) {
            CatalogVO vo = new CatalogVO();

            vo.setDestName(data.get("_id").toString());

            List<Map<String, Object>> mm = new ArrayList<>();
            List<Object> names = (List<Object>) data.get("names");
            List<Object> ids = (List<Object>) data.get("ids");
            for (int i = 0;i < names.size(); i++) {
                Map<String, Object> vv = new HashMap<>();
                String name = names.get(i).toString();
                String id = ids.get(i).toString();
                vv.put("id", id);
                vv.put("name", name);
                mm.add(vv);
            }
            vo.setMapList(mm);
            list.add(vo);
        }
        System.out.println(list);
        return list;
    }

    public List<StrategyCatalog> queryCatalogByDestId(String destId) {

        //攻略分类
        List<StrategyCatalog> list = repository.findByDestId(destId);

        //攻略分类下所有的攻略文章, limit 0 3
        for (StrategyCatalog catalog : list) {
            List<Strategy> sts = strategyService.getStrategyByCatalogId(catalog.getId());
            catalog.setStrategies(sts);
        }

        return list;
    }
}
