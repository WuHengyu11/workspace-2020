package cn.wolfcode.service;

import cn.wolfcode.domain.Role;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IRoleService {

    PageInfo query(QueryObject qo);

    List<Role> listAll();

    Role getById(Long id);

    void update(Role role, Long[] ids);

    void save(Role role, Long[] ids);

}
