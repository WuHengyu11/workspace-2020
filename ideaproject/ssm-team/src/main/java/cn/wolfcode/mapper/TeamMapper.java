package cn.wolfcode.mapper;

import cn.wolfcode.domain.Team;
import cn.wolfcode.qo.QueryObject;

import java.util.List;

public interface TeamMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Team record);
    Team selectByPrimaryKey(Long id);
    List<Team> selectAll();
    int updateByPrimaryKey(Team record);
    int queryForCount(QueryObject qo);
    List<Team> queryForList(QueryObject qo);
}