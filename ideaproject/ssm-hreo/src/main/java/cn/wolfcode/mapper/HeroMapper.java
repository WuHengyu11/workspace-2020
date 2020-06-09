package cn.wolfcode.mapper;

import cn.wolfcode.domain.Hero;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;

import java.util.List;

public interface HeroMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Hero record);
    Hero selectByPrimaryKey(Long id);
    List<Hero> selectAll();
    int updateByPrimaryKey(Hero record);
    int queryForCout(QueryObject qo);
    List<Hero> queryForList(QueryObject qo);
}