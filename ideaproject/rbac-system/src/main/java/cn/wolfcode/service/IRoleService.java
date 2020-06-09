package cn.wolfcode.service;

import cn.wolfcode.domain.Role;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;

import java.util.List;

public interface IRoleService {
    void save(Role role,Long[]ids);
    void delete(Long id);
    void update(Role role,Long[]ids);
    Role get(Long id);
    List<Role> listAll();
    PageResult<Role> query(QueryObject qo);
}
