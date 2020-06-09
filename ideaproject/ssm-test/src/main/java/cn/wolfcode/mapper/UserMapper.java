package cn.wolfcode.mapper;

import cn.wolfcode.domain.User;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);
    int insert(User record);
    User selectByPrimaryKey(Long id);
    List<User> selectAll();
    int updateByPrimaryKey(User record);
    int queryForCount(QueryObject qo);
    List<User> queryForList(QueryObject qo);
}