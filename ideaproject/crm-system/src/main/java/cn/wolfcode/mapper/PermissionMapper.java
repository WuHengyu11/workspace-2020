package cn.wolfcode.mapper;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.qo.QueryObject;

import java.util.List;

public interface PermissionMapper {
    void deleteByPrimaryKey(Long id);
    void insert(Permission permission);
    Permission selectByPrimaryKey(Long id);
    List<Permission> selectAll();
    List<Permission> queryForList(QueryObject qo);
    List<String> selectAllExpression();
    List<String> selectExpressionByEmpId(Long id);
}