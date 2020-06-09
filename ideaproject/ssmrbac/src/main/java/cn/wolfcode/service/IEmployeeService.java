package cn.wolfcode.service;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;

import java.util.List;

public interface IEmployeeService {
    void save(Employee dept);
    void delete(Long id);
    void update(Employee dept);
    Employee get(Long id);
    List<Employee> listAll();
    PageResult<Employee> query(QueryObject qo);
}
