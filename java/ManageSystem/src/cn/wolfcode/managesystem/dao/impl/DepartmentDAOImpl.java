package cn.wolfcode.managesystem.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.wolfcode.managesystem.dao.IDepartmentDAO;
import cn.wolfcode.managesystem.domain.Department;
import cn.wolfcode.managesystem.query.QueryObject;
import cn.wolfcode.managesystem.util.MyBatisUtil;

public class DepartmentDAOImpl implements IDepartmentDAO {

	@Override
	public void insert(Department dt) {
		//获取连接
		SqlSession session = MyBatisUtil.getConnection();
		
		//执行sql
		session.insert("cn.wolfcode.managesystem.mapper.DepartmentMapper.insert",dt);
		
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
		session.delete("cn.wolfcode.managesystem.mapper.DepartmentMapper.delete",id);
		
		//执行事务
		session.commit();
		
		//释放资源
		session.close();
		
	}

	@Override
	public void update(Department dt) {
		//获取连接
		SqlSession session = MyBatisUtil.getConnection();
		
		//执行sql
		session.update("cn.wolfcode.managesystem.mapper.DepartmentMapper.update",dt);
		
		//执行事务
		session.commit();
		
		//释放资源
		session.close();
		
	}

	@Override
	public Department selectOne(Long id) {
		//获取连接
		SqlSession session = MyBatisUtil.getConnection();
		
		//执行sql
		Department dt = session.selectOne("cn.wolfcode.managesystem.mapper.DepartmentMapper.selectOne",id);
		
		//释放资源
		session.close();
		return dt;
	}

	@Override
	public int queryForCount(QueryObject qo) {
		//获取连接
		SqlSession session = MyBatisUtil.getConnection();
		
		//执行sql
		 Integer rows = session.selectOne("cn.wolfcode.managesystem.mapper.DepartmentMapper.selectForCount",qo);
		
		//释放资源
		session.close();

		return rows;
	}

	@Override
	public List<?> queryList(QueryObject qo) {
		//获取连接
		SqlSession session = MyBatisUtil.getConnection();
		
		//执行sql
		 List<?> result = session.selectList("cn.wolfcode.managesystem.mapper.DepartmentMapper.queryForList",qo);
		
		//释放资源
		session.close();

		return result;
	}

	
}
