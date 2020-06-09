package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.StrategyTheme;
import cn.wolfcode.luowowo.query.StrategyThemeQuery;
import cn.wolfcode.luowowo.repository.StrategyThemeRepository;
import cn.wolfcode.luowowo.service.IStrategyThemeService;
import cn.wolfcode.luowowo.util.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
//@Transactional  暂时先别管事务
public class StrategyThemeServiceImpl implements IStrategyThemeService {

    @Autowired
    private StrategyThemeRepository repository;

    @Autowired
    private MongoTemplate template;


    public StrategyTheme get(String id) {
        Optional<StrategyTheme> optional = repository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    //page对象里面包括: 总页数 每页显示条数......
    public Page<StrategyTheme> query(StrategyThemeQuery qo) {
        Query query = new Query();
       
        return DBHelper.query(template, StrategyTheme.class, query, qo.getPageable());
    }

    public void saveOrUpdate(StrategyTheme theme) {
        if(!StringUtils.hasLength(theme.getId())){
            //添加
            theme.setId(null);
            repository.save(theme);
        }else{

            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(theme.getId()));


            //DBHelper.update(template, query, region, "name","sn","refIds","ishot","sequence","info");

        }
    }
    public void delete(String id) {
        repository.deleteById(id);
    }

    public List<StrategyTheme> list() {
        return repository.findAll();
    }
}
