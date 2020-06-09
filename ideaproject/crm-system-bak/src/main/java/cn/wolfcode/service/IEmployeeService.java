package cn.wolfcode.service;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IEmployeeService {
    void save(Employee employee, Long[] ids);
    void delete(Long id);
    void update(Employee employee, Long[] ids);
    Employee get(Long id);
    List<Employee> listAll();
    PageInfo<Employee> query(QueryObject qo);
    Employee login(String username, String password);
    void updatePwd(String password, Long id);
    void batchDelete(Long[] ids);
}
