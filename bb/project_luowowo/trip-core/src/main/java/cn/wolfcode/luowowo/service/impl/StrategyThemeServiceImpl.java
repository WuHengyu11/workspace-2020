package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.StrategyTheme;
import cn.wolfcode.luowowo.query.StrategyThemeQuery;
import cn.wolfcode.luowowo.repository.StrategyThemeRepository;
import cn.wolfcode.luowowo.service.IStrategyThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class StrategyThemeServiceImpl implements IStrategyThemeService {
    @Autowired
    private StrategyThemeRepository strategyThemeRepository;
    @Autowired
    private MongoTemplate template;
    public void save(StrategyTheme strategyTheme) {
        strategyTheme.setId(null);
        strategyThemeRepository.save(strategyTheme);
    }

    @Override
    public void delete(String id) {
        strategyThemeRepository.deleteById(id);
    }

    @Override
    public void update(StrategyTheme strategyTheme) {
        strategyThemeRepository.save(strategyTheme);
    }

    @Override
    public StrategyTheme get(String id) {
        Optional<StrategyTheme> strategyTheme = strategyThemeRepository.findById(id);
        if (strategyTheme.isPresent()){
            return strategyTheme.get();
        }
        return null;
    }

    @Override
    public List<StrategyTheme> listAll() {
        return strategyThemeRepository.findAll();
    }

    @Override
    public Page query(StrategyThemeQuery qo) {
        //在mongodb中没有针对高级查询的分页操作,需要自己定义
        //自己封装分页对象需要注意的2个核心
        //分页条件对象
        Query query = new Query();
        //总记录数
        long count = template.count(query, StrategyTheme.class);
        if (count == 0){
            return Page.empty();
        }
        //每页显示记录
        Pageable pageable = PageRequest.of(qo.getCurrentPage()-1,qo.getPageSize());
        query.with(pageable);
        List<StrategyTheme> strategyThemeList = template.find(query, StrategyTheme.class);
        return new PageImpl(strategyThemeList,pageable,count);
    }

    @Override
    public void saveOrUpdate(StrategyTheme strategyTheme) {
        if (!StringUtils.hasLength(strategyTheme.getId())){
            this.save(strategyTheme);
        }else{
            this.update(strategyTheme);
        }
    }
}
