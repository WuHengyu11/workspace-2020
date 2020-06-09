package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Department;
import cn.wolfcode.mapper.DepartmentMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IDepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    @Transactional
    public void save(Department dep) {
        departmentMapper.save(dep);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        departmentMapper.delete(id);
    }

    @Override
    @Transactional
    public void update(Department dep) {
        departmentMapper.update(dep);
    }

    @Override
    @Transactional
    public Department selectOne(Long id) {
        Department department = departmentMapper.selectOne(id);
        System.out.println(department);
        return department;
    }

    @Override
    @Transactional
    public List<Department> selectAll() {
        List<Department> depts = departmentMapper.selectAll();
        System.out.println(depts);
        return depts;
    }

    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Department> list = departmentMapper.selectForList(qo);
        return new PageInfo(list);
    }
}
