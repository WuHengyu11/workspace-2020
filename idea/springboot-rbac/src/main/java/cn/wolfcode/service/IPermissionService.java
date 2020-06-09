package cn.wolfcode.service;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IPermissionService {


    void delete(Long id);

    Permission selectOne(Long id);

    List<Permission> selectAll();

    PageInfo query(QueryObject qo);

    void reload();
    List<String> selectExpressionByEmpid(Long id);
}
