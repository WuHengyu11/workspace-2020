package cn.wolfcode.service;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IEmployeeService {

    void save(Employee employee, Long[] ids);

    void delete(Long id);

    void update(Employee employee, Long[] ids);

    Employee selectOne(Long id);

    List<Employee> selectAll();

    PageInfo query(QueryObject qo);

    Employee login(String username, String password);

    Employee updatePwd(Long id, String password);


    Employee resetPwd(Long id, String newPassword);
}
