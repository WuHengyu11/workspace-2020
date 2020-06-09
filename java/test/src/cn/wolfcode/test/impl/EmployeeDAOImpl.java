package cn.wolfcode.test.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.wolfcode.test.dao.IEmployeeDAO;
import cn.wolfcode.test.domain.Employee;
import cn.wolfcode.test.util.EmployeeUtil;

public class EmployeeDAOImpl implements IEmployeeDAO {

	@Override
	public void insert(Employee ee) {
		//获取链接
		SqlSession session = EmployeeUtil.getConnection();
		
		//执行sql语句
		session.insert("cn.wolfcode.test.mapper.EmployeeMapper.insert",ee);
		
		//执行事务
		session.commit();
		
		//释放资源
		session.close();

	}

	@Override
	public void delete(long id) {
		//获取链接
		SqlSession session = EmployeeUtil.getConnection();
		
		//执行sql语句
		session.delete("cn.wolfcode.test.mapper.EmployeeMapper.delete",id);
		
		//执行事务
		session.commit();
		
		//释放资源
		session.close();

	}

	@Override
	public void update(Employee ee) {
		//获取链接
		SqlSession session = EmployeeUtil.getConnection();
		
		//执行sql语句
		session.update("cn.wolfcode.test.mapper.EmployeeMapper.update",ee);
		
		//执行事务
		session.commit();
		
		//释放资源
		session.close();


	}

	@Override
	public Employee selectOne(long id) {
		//获取链接
		SqlSession session = EmployeeUtil.getConnection();
		
		//执行sql语句
		Employee ee = session.selectOne("cn.wolfcode.test.mapper.EmployeeMapper.selectOne", id);
		
		//执行事务
		session.commit();
		
		//释放资源
		session.close();
		
		//返回结果
		return ee;
	}

	@Override
	public List<Employee> selectAll() {
		//获取链接
		SqlSession session = EmployeeUtil.getConnection();
		
		//执行sql语句
		List<Employee> list = session.selectList("cn.wolfcode.test.mapper.EmployeeMapper.selectAll");
		
		//执行事务
		session.commit();
		
		//释放资源
		session.close();
		
		return list;
	}

}
