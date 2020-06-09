package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Hero;
import cn.wolfcode.mapper.HeroMapper;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class HeroServiceImpl implements IHeroService {
    @Autowired
    private HeroMapper heroMapper;
    @Override
    public void save(Hero h) {
        heroMapper.insert(h);
    }

    @Override
    public void delete(Long id) {
        heroMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Hero h) {
        heroMapper.updateByPrimaryKey(h);
    }

    @Override
    public Hero get(Long id) {
        return heroMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Hero> listAll() {
        return heroMapper.selectAll();
    }

    @Override
    public PageResult<Hero> query(QueryObject qo) {
        int count = heroMapper.queryForCout(qo);
        if (count == 0){
            return new PageResult<>(Collections.emptyList(),qo.getCurrentPage(),qo.getPageSize(),count);
        }
        List<Hero> heros = heroMapper.queryForList(qo);
        return new PageResult<>(heros,qo.getCurrentPage(),qo.getPageSize(),count);
    }
}
