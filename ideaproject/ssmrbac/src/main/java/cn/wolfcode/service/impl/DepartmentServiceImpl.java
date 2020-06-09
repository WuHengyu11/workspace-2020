package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Department;
import cn.wolfcode.mapper.DepartmentMapper;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    @Override
    public void save(Department dept) {
        departmentMapper.insert(dept);
    }

    @Override
    public void delete(Long id) {
        departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Department dept) {
        departmentMapper.updateByPrimaryKey(dept);
    }

    @Override
    public Department get(Long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> listAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public PageResult<Department> query(QueryObject qo) {
        int count = departmentMapper.queryForCount(qo);
        if (count == 0){
            return new PageResult<>(Collections.emptyList(),qo.getCurrentPage(),qo.getPageSize(),count);
        }
        List<Department> departments = departmentMapper.queryForList(qo);
        return new PageResult<>(departments,qo.getCurrentPage(),qo.getPageSize(),count);
    }
}
