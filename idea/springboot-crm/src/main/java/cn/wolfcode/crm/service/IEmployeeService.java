package cn.wolfcode.crm.service;

import java.util.List;

public interface IEmployeeService {
    void save(Employee employee);
    void delete(Long id);
    void update(Employee employee);
    Employee get(Long id);
    List<Employee>selectAll();

    List<Employee> selectByEmp(String...emp);
}
