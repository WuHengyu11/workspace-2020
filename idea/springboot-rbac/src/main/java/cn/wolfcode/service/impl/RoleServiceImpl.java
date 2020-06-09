package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.domain.Role;
import cn.wolfcode.mapper.RoleMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Role> list = roleMapper.selectForList(qo);
        return new PageInfo(list);
    }

    @Override
    public List<Role> listAll() {
        return roleMapper.selectAll();
    }

    @Override
    public Role getById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Role role, Long[] ids) {
        roleMapper.deleteByPrimaryKey(role);
        roleMapper.deleteRelation(role.getId());
        if(ids != null &&ids.length > 0 ){
            for (Long pid : ids) {
                roleMapper.insertRelation(role.getId(),pid);
            }
        }
    }

    @Override
    public void save(Role role, Long[] ids) {
        roleMapper.insert(role);

        if(ids != null &&ids.length > 0 ){
            for (Long pid : ids) {
                roleMapper.insertRelation(role.getId(),pid);
            }
        }
    }
}
