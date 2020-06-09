package cn.wolfcode.mapper;

import cn.wolfcode.domain.Role;
import cn.wolfcode.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Role id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    int selectForCount(QueryObject qo);
    //不用管
    List<Role> selectForList(QueryObject qo);

    void insertRelation(@Param("role_id") Long role_id, @Param("permission_id") Long permission_id);

    void deleteRelation(Long role_id);
}