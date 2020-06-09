package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.domain.StrategyCatalog;
import cn.wolfcode.luowowo.domain.StrategyTheme;
import cn.wolfcode.luowowo.query.StrategyQuery;
import cn.wolfcode.luowowo.redis.vo.StrategyStatisVO;
import cn.wolfcode.luowowo.repository.StrategyRepository;
import cn.wolfcode.luowowo.service.IDestinationService;
import cn.wolfcode.luowowo.service.IStrategyCatalogService;
import cn.wolfcode.luowowo.service.IStrategyService;
import cn.wolfcode.luowowo.service.IStrategyThemeService;
import cn.wolfcode.luowowo.util.DBHelper;
import org.springframework.beans.BeanUtils;
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
public class StrategyServiceImpl implements IStrategyService {

    @Autowired
    private StrategyRepository repository;

    @Autowired
    private MongoTemplate template;

    @Autowired
    private IStrategyCatalogService strategyCatalogService;
    @Autowired
    private IStrategyThemeService strategyThemeService;

    @Autowired
    private IDestinationService destinationService;


    public Strategy get(String id) {
        Optional<Strategy> optional = repository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    //page对象里面包括: 总页数 每页显示条数......
    public Page<Strategy> query(StrategyQuery qo) {
        Query query = new Query();

        //通过目的地方式查询
        if(StringUtils.hasLength(qo.getDestId())){
            query.addCriteria(Criteria.where("destId").is(qo.getDestId()));
        }

        //主题
        if(StringUtils.hasLength(qo.getThemeId())){
            query.addCriteria(Criteria.where("themeId").is(qo.getThemeId()));
        }

        return DBHelper.query(template, Strategy.class, query, qo.getPageable());
    }

    public void saveOrUpdate(Strategy strategy) {
        //1:目的地名称与id
        StrategyCatalog catalog = strategyCatalogService.get(strategy.getCatalogId());
        strategy.setDestId(catalog.getDestId());
        strategy.setDestName(catalog.getDestName());
        //2:主题名称
        StrategyTheme theme = strategyThemeService.get(strategy.getThemeId());
        strategy.setThemeName(theme.getName());
        //3:分类名称
        strategy.setCatalogName(catalog.getName());
        //4:是否国外
        List<Destination> toasts = destinationService.getToasts(catalog.getDestId());
        if (toasts != null && toasts.size() > 0){
            if("中国".equals(toasts.get(0).getName())){
                strategy.setIsabroad(Strategy.ABROAD_NO);
            }else{
                strategy.setIsabroad(Strategy.ABROAD_YES);
            }
        }
        if(!StringUtils.hasLength(strategy.getId())){
            //添加
            strategy.setId(null);
            //5:创建时间
            strategy.setCreateTime(new Date());
            repository.save(strategy);
        }else{
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(strategy.getId()));
            DBHelper.update(template, query, strategy,
                    "destId","destName","themeId",
                    "themeName","catalogId","catalogName",
                    "title","subTitle","summary","coverUrl",
                    "isabroad","state","content");

        }
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public List<Strategy> getStrategyByCatalogId(String catalogId) {
        return repository.findByCatalogId(catalogId);
    }

    public List<Strategy> getViewnumTop3(String destId) {
        //点击量前3
        Pageable of = PageRequest.of(0, 3, Sort.Direction.DESC, "viewnum");
        return repository.findByDestId(destId, of);
    }

    @Override
    public List<Strategy> list() {
        return repository.findAll();
    }

    @Override
    public void updateStatisVo(StrategyStatisVO vo) {

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(vo.getStrategyId()));


        //DBHelper 工具类中共需要明确指定表名， 集合名，Strategy类字节码可以找到
        Strategy strategy = new Strategy();
        BeanUtils.copyProperties(vo, strategy);

        DBHelper.update(template, query, strategy, "favornum",
                "replynum","sharenum","thumbsupnum","viewnum");
    }

    @Override
    public List<Strategy> findByDestName(String keyword) {
        return repository.findByDestName(keyword);
    }
}
