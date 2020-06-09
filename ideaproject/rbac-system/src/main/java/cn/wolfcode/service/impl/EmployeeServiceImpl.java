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
    //关联mapper
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public void save(Employee employee,Long[] ids) {
        employeeMapper.insert(employee);
        if(ids != null && ids.length > 0){
            for(Long rid : ids){
                employeeMapper.insertRelation(employee.getId(), rid);
            }
        }
    }

    @Override
    public void delete(Long id) {
        employeeMapper.deleteByPrimaryKey(id);
        //删除关联关系
        employeeMapper.deleteRelation(id);
    }

    @Override
    public void update(Employee employee,Long[] ids) {
        employeeMapper.updateByPrimaryKey(employee);
        //删除关联关系
        employeeMapper.deleteRelation(employee.getId());
        //重新关联关系
        if(ids != null && ids.length > 0){
            for(Long rid : ids){
                employeeMapper.insertRelation(employee.getId(), rid);
            }
        }
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
        if (count == 0) {
            return new PageResult<>(Collections.emptyList(),qo.getCurrentPage(),qo.getPageSize(),count);
        }
        List<Employee> employees = employeeMapper.queryForList(qo);
        return new PageResult<>(employees,qo.getCurrentPage(),qo.getPageSize(),count);
    }

    @Override
    public Employee login(String username, String password) {
        //根据用户名username查询数据库
        Employee employee = employeeMapper.selectByusernameAndPassword(username,password);
        if (employee==null){
            throw new RuntimeException("账号和密码不匹配");
        }
        return employee;
    }

    @Override
    public void updatePwd(String password, Long id) {
        employeeMapper.updatePwd(password,id);
    }
}
