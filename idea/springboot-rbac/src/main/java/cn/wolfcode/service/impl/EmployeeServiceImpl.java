package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.mapper.EmployeeMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IEmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    @Transactional
    public void save(Employee employee, Long[] ids) {
        employeeMapper.insert(employee);
        /*if(ids != null && ids.length > 0){
            for (Long roleId : ids) {
                employeeMapper.insertRelation(employee.getId(),roleId);
            }*/
        if(ids != null && ids.length > 0){
            for (Long roleId : ids) {
                employeeMapper.insertRelation2(employee.getId(), ids);
            }
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        employeeMapper.deleteByPrimaryKey(id);
        //删除  关联的表  中的id值
        employeeMapper.deleteRelation(id);
    }

    @Override
    @Transactional
    public void update(Employee employee, Long[] ids) {
        employeeMapper.updateByPrimaryKey(employee);
        //删除关联
        employeeMapper.deleteRelation(employee.getId());
        //重新关联
        if(ids != null && ids.length > 0){
            employeeMapper.insertRelation2(employee.getId(),ids);
        }
    }

    @Override
    @Transactional
    public Employee selectOne(Long id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    @Override
    @Transactional
    public List<Employee> selectAll() {
        List<Employee> employeets = employeeMapper.selectAll();
        System.out.println(employeets);
        return employeets;
    }
    //调用Mapper接口实现类封装成PageResult返回
    @Override
    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Employee> list = employeeMapper.selectForList(qo);
        return new PageInfo(list);
    }

    @Override
    public Employee login(String username, String password) {
        //查询账户是否存在
        Employee employee = employeeMapper.selectByUsernameAndPassword(username,password);
        System.out.println(employee);
        if(employee == null){
            throw new RuntimeException("账号或密码错误");
        }
        return employee;
    }

    @Override
    public Employee updatePwd(Long id,String password) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        if(employee.getPassword() != password){
            employee.setPassword(password);
            employeeMapper.updateByPrimaryKey(employee);
        }
        return employee;
    }

    @Override
    public Employee resetPwd(Long id, String newPassword) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        if(newPassword!=null){
            employee.setPassword(newPassword);
            employeeMapper.updateByPrimaryKey(employee);
        }
        return employee;
    }


}
