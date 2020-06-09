package cn.wolfcode.service;

import cn.wolfcode.domain.Hero;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;

import java.util.List;

public interface IHeroService {
    void save(Hero h);
    void delete(Long id);
    void update(Hero h);
    Hero get(Long id);
    List<Hero> listAll();
    PageResult<Hero> query(QueryObject qo);
}
