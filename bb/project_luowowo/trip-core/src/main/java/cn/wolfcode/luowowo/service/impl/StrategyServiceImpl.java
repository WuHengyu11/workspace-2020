package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.domain.StrategyCatalog;
import cn.wolfcode.luowowo.domain.StrategyTheme;
import cn.wolfcode.luowowo.query.StrategyQuery;
import cn.wolfcode.luowowo.repository.StrategyRepository;
import cn.wolfcode.luowowo.service.*;
import cn.wolfcode.luowowo.service.IStrategyService;
import cn.wolfcode.luowowo.util.DBHelper;
import io.lettuce.core.GeoArgs;
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
public class StrategyServiceImpl implements IStrategyService {
    @Autowired
    private StrategyRepository strategyRepository;
    @Autowired
    private MongoTemplate template;
    @Autowired
    private IStrategyCatalogService strategyCatalogService;
    @Autowired
    private IStrategyThemeService strategyThemeService;
    @Autowired
    private IDestinationService destinationService;

    @Override
    public void save(Strategy strategy) {
        strategy.setId(null);
        strategyRepository.save(strategy);
    }

    @Override
    public void delete(String id) {
        strategyRepository.deleteById(id);
    }

    @Override
    public void update(Strategy strategy) {
        strategyRepository.save(strategy);
    }

    @Override
    public Strategy get(String id) {
        Optional<Strategy> strategy = strategyRepository.findById(id);
        if (strategy.isPresent()){
            return strategy.get();
        }
        return null;
    }

    @Override
    public List<Strategy> listAll() {
        return strategyRepository.findAll();
    }

    @Override
    public Page query(StrategyQuery qo) {
        //在mongodb中没有针对高级查询的分页操作,需要自己定义
        //自己封装分页对象需要注意的2个核心
        //分页条件对象
        Query query = new Query();
        if (StringUtils.hasLength(qo.getDestId())){
            query.addCriteria(Criteria.where("destId").is(qo.getDestId()));
        }
        if (StringUtils.hasLength(qo.getThemeId())){
            query.addCriteria(Criteria.where("themeId").is(qo.getThemeId()));
        }
        //总记录数
        long count = template.count(query, Strategy.class);
        if (count == 0){
            return Page.empty();
        }
        //每页显示记录
        Pageable pageable = PageRequest.of(qo.getCurrentPage()-1,qo.getPageSize());
        query.with(pageable);
        List<Strategy> strategyList = template.find(query, Strategy.class);
        return new PageImpl(strategyList,pageable,count);
    }

    @Override
    public void saveOrUpdate(Strategy strategy) {
        //目的地id与名称
        StrategyCatalog catalog = strategyCatalogService.get(strategy.getCatalogId());
        strategy.setDestId(catalog.getDestId());
        strategy.setDestName(catalog.getDestName());

        //是否国外
        List<Destination> toasts = destinationService.getToasts(catalog.getDestId());
        if (toasts != null && toasts.size() > 0){
            Destination destination = toasts.get(0);
            if ("中国".equals(destination.getName())){
                strategy.setIsabroad(Strategy.ABROAD_NO);
            }else{
                strategy.setIsabroad(Strategy.ABROAD_YES);
            }
        }
        //主题名字
        StrategyTheme strategyTheme = strategyThemeService.get(strategy.getThemeId());
        strategy.setThemeName(strategyTheme.getName());

        //分类名字
        strategy.setCatalogName(catalog.getName());

        if (!StringUtils.hasLength(strategy.getId())){
            //创建时间
            strategy.setCreateTime(new Date());
            this.save(strategy);
        }else{
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(strategy.getId()));
            DBHelper.update(
                    template,query,strategy,"destId","destName","themeId","themeName","catalogId","catalogName","title","subTitle","summary","coverUrl","isabroad","state","content"
            );
            this.update(strategy);
        }

    }

    @Override
    public List<Strategy> queryByCatalogId(String catalogId) {
        return strategyRepository.findByCatalogId(catalogId);
    }

    @Override
    public List<Strategy> queryViewnumTop3(String destId) {
        //指定目的地的 倒序排列 前三
        Pageable pageable = PageRequest.of(0,3, Sort.Direction.DESC,"viewnum");
        return strategyRepository.findByDestId(destId,pageable);
    }
}
