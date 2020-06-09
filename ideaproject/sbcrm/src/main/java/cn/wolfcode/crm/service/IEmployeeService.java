package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.query.EmployeeQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IEmployeeService {
    Employee get(Long id);
    List<Employee> listAll();
    PageInfo query(EmployeeQueryObject qo);
}
