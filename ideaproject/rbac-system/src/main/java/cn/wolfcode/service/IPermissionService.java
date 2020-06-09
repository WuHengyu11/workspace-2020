package cn.wolfcode.service;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;

import java.util.List;

public interface IPermissionService {
    void delete(Long id);
    Permission get(Long id);
    List<Permission> listAll();
    PageResult<Permission> query(QueryObject qo);
    void reload();
    List<String> selectExpressionByEmpId(Long id);
}
