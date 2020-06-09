package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.domain.StrategyCatalog;
import cn.wolfcode.luowowo.query.StrategyCatalogQuery;
import cn.wolfcode.luowowo.repository.StrategyCatalogRepository;
import cn.wolfcode.luowowo.service.IStrategyCatalogService;
import cn.wolfcode.luowowo.service.IStrategyService;
import cn.wolfcode.luowowo.vo.CatalogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class StrategyCatalogServiceImpl implements IStrategyCatalogService {
    @Autowired
    private StrategyCatalogRepository strategyCatalogRepository;
    @Autowired
    private MongoTemplate template;
    @Autowired
    private IStrategyService strategyService;
    @Override
    public void save(StrategyCatalog strategyCatalog) {
        strategyCatalog.setId(null);
        strategyCatalogRepository.save(strategyCatalog);
    }

    @Override
    public void delete(String id) {
        strategyCatalogRepository.deleteById(id);
    }

    @Override
    public void update(StrategyCatalog strategyCatalog) {
        strategyCatalogRepository.save(strategyCatalog);
    }

    @Override
    public StrategyCatalog get(String id) {
        Optional<StrategyCatalog> strategyCatalog = strategyCatalogRepository.findById(id);
        if (strategyCatalog.isPresent()){
            return strategyCatalog.get();
        }
        return null;
    }

    @Override
    public List<StrategyCatalog> listAll() {
        return strategyCatalogRepository.findAll();
    }

    @Override
    public Page query(StrategyCatalogQuery qo) {
        //在mongodb中没有针对高级查询的分页操作,需要自己定义
        //自己封装分页对象需要注意的2个核心
        //分页条件对象
        Query query = new Query();
        //总记录数
        long count = template.count(query, StrategyCatalog.class);
        if (count == 0){
            return Page.empty();
        }
        //每页显示记录
        Pageable pageable = PageRequest.of(qo.getCurrentPage()-1,qo.getPageSize());
        query.with(pageable);
        List<StrategyCatalog> strategyCatalogList = template.find(query, StrategyCatalog.class);
        return new PageImpl(strategyCatalogList,pageable,count);
    }

    @Override
    public void saveOrUpdate(StrategyCatalog strategyCatalog) {
        if (!StringUtils.hasLength(strategyCatalog.getId())){
            this.save(strategyCatalog);
        }else{
            //this.update(strategyCatalog);

            //部分字段更新
            //Query query = new Query();
            //query.addCriteria(Criteria.where("_id").is(strategyCatalog.getId()));
            //Update update = new Update();
            //update.set("name",strategyCatalog.getName());
            //template.updateMulti(query,update,StrategyCatalog.class);
        }

    }

    @Override
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

    @Override
    public List<StrategyCatalog> queryCatalogByDestId(String destId) {
        //所有分类攻略
        List<StrategyCatalog> list = strategyCatalogRepository.findByDestId(destId);

        //攻略分类下关联的攻略对象集合
        for (StrategyCatalog strategyCatalog : list) {
            List<Strategy> sts = strategyService.queryByCatalogId(strategyCatalog.getId());
            strategyCatalog.setStrategies(sts);
        }
        return list;
    }
}
