package cn.wolfcode.managesystem.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.wolfcode.managesystem.dao.IEmployeeDAO;
import cn.wolfcode.managesystem.domain.Employee;
import cn.wolfcode.managesystem.query.QueryObject;
import cn.wolfcode.managesystem.util.MyBatisUtil;

public class EmployeeDAOImpl implements IEmployeeDAO {

	@Override
	public void insert(Employee e) {
		//获取连接
		SqlSession session = MyBatisUtil.getConnection();
		
		//执行sql
		session.insert("cn.wolfcode.managesystem.mapper.EmployeeMapper.insert",e);
		
		//执行事务
		session.commit();
		
		//释放资源
		session.close();
	}

	@Override
	public void delete(Long id) {
		//获取连接
		SqlSession session = MyBatisUtil.getConnection();
		
		//执行sql
		session.delete("cn.wolfcode.managesystem.mapper.EmployeeMapper.delete",id);
		
		//执行事务
		session.commit();
		
		//释放资源
		session.close();
	}

	@Override
	public void update(Employee e) {
		//获取连接
		SqlSession session = MyBatisUtil.getConnection();
		
		//执行sql
		session.update("cn.wolfcode.managesystem.mapper.EmployeeMapper.update",e);
		
		//执行事务
		session.commit();
		
		//释放资源
		session.close();
	}

	@Override
	public Employee selectOne(Long id) {
		//获取连接
		SqlSession session = MyBatisUtil.getConnection();
		
		//执行sql
		Employee e = session.selectOne("cn.wolfcode.managesystem.mapper.EmployeeMapper.selectOne",id);
		
		//释放资源
		session.close();
		return e;
	}

	@Override
	public int queryForCount(QueryObject qo) {
		//获取连接
		SqlSession session = MyBatisUtil.getConnection();
		
		//执行sql
		 Integer rows = session.selectOne("cn.wolfcode.managesystem.mapper.EmployeeMapper.selectForCount",qo);
		
		//释放资源
		session.close();

		return rows;
	}

	@Override
	public List<?> queryList(QueryObject qo) {
		//获取连接
		SqlSession session = MyBatisUtil.getConnection();
		
		//执行sql
		 List<?> result = session.selectList("cn.wolfcode.managesystem.mapper.EmployeeMapper.queryForList",qo);
		
		//释放资源
		session.close();

		return result;
	}
}
