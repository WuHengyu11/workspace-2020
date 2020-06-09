package cn.wolfcode.employeetest.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.wolfcode.employeetest.dao.IEmployeeDAO;
import cn.wolfcode.employeetest.domain.Employee;
import cn.wolfcode.employeetest.util.EmployeeUtil;

public class EmployeeDAOImpl implements IEmployeeDAO {

	@Override
	public void insert(Employee e) {
		//获取连接
		SqlSession session = EmployeeUtil.getConnection();
		
		//执行sql
		session.insert("cn.wolfcode.employeetest.mapper.EmployeeMapper.insert",e);
		
		//执行事务
		session.commit();
		
		//释放资源
		session.close();
	}

	@Override
	public void delete(long id) {
		//获取连接
		SqlSession session = EmployeeUtil.getConnection();
		
		//执行sql
		session.delete("cn.wolfcode.employeetest.mapper.EmployeeMapper.delete",id);
		
		//执行事务
		session.commit();
		
		//释放资源
		session.close();

	}

	@Override
	public void update(Employee e) {
		//获取连接
		SqlSession session = EmployeeUtil.getConnection();
		
		//执行sql
		session.update("cn.wolfcode.employeetest.mapper.EmployeeMapper.update",e);
		
		//执行事务
		session.commit();
		
		//释放资源
		session.close();

	}

	@Override
	public Employee selectOne(long id) {
		//获取连接
		SqlSession session = EmployeeUtil.getConnection();
		
		//执行sql
		Employee e = session.selectOne("cn.wolfcode.employeetest.mapper.EmployeeMapper.selectOne",id);
		
		//释放资源
		session.close();
		return e;
	}

	@Override
	public List<Employee> selectAll() {
		//获取连接
		SqlSession session = EmployeeUtil.getConnection();
		
		//执行sql
		List<Employee> employeeList = session.selectList("cn.wolfcode.employeetest.mapper.EmployeeMapper.selectAll");
		
		//释放资源
		session.close();
		return employeeList;
	}

}
