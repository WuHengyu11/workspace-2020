package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.mapper.EmployeeMapper;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Override
    public void save(Employee dept) {
        employeeMapper.insert(dept);
    }

    @Override
    public void delete(Long id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Employee dept) {
        employeeMapper.updateByPrimaryKey(dept);
    }

    @Override
    public Employee get(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> listAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public PageResult<Employee> query(QueryObject qo) {
        int count = employeeMapper.queryForCount(qo);
        if (count == 0){
            return new PageResult<>(Collections.emptyList(),qo.getCurrentPage(),qo.getPageSize(),count);
        }
        List<Employee> employees = employeeMapper.queryForList(qo);
        return new PageResult<>(employees,qo.getCurrentPage(),qo.getPageSize(),count);
    }
}
