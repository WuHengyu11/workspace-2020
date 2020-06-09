package cn.wolfcode.javaweb2.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.wolfcode.javaweb2.dao.IEmployeeDAO;
import cn.wolfcode.javaweb2.domain.Employee;
import cn.wolfcode.javaweb2.util.MybaitsUtil;

public class EmployeeDAOImpl implements IEmployeeDAO {

	@Override
	public void insert(Employee e) {
		//获取连接
		SqlSession session = MybaitsUtil.getConnection();
		
		//执行sql
		session.insert("cn.wolfcode.javaweb2.mapper.EmployeeMapper.insert",e);
		
		//执行事务
		session.commit();
		
		//释放资源
		session.close();
	}

	@Override
	public void delete(long id) {
		//获取连接
		SqlSession session = MybaitsUtil.getConnection();
		
		//执行sql
		session.delete("cn.wolfcode.javaweb2.mapper.EmployeeMapper.delete", id);
		
		//执行事务
		session.commit();
		
		//释放资源
		session.close();
		

	}

	@Override
	public void update(Employee e) {
		//获取连接
		SqlSession session = MybaitsUtil.getConnection();
		
		//执行sql
		session.update("cn.wolfcode.javaweb2.mapper.EmployeeMapper.update", e);
		
		//执行事务
		session.commit();
		
		//释放资源
		session.close();

	}

	@Override
	public Employee selectOne(long id) {
		//获取连接
		SqlSession session = MybaitsUtil.getConnection();
		
		//执行sql
		Employee e = session.selectOne("cn.wolfcode.javaweb2.mapper.EmployeeMapper.selectOne", id);
		
		//释放资源
		session.close();
		return e;
	}

	@Override
	public List<Employee> selectAll() {
		//获取连接
		SqlSession session = MybaitsUtil.getConnection();
		
		//执行sql
		List<Employee> EmployeeList = session.selectList("cn.wolfcode.javaweb2.mapper.EmployeeMapper.selectAll");
		
		//释放资源
		session.close();
		return EmployeeList;
	}

}
