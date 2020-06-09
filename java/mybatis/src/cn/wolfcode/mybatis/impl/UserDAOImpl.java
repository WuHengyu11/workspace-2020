package cn.wolfcode.mybatis.impl;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.wolfcode.mybatis.dao.IUserDAO;
import cn.wolfcode.mybatis.domain.User;
import cn.wolfcode.mybatis.util.UserDAOUtil;

/**
* @author 作者: 邬恒宇
* @version 创建时间：2020年2月28日 下午9:24:43
*/
public class UserDAOImpl implements IUserDAO{
	@Override
	public void insert(User u) {
		//调取工具类获取连接
		SqlSession session = UserDAOUtil.openSession();
		
		//执行SQL
		session.insert("cn.wolfcode.mybatis.mapper.UserMapper.insert",u);
		
		//提交事务
		session.commit();
		
		//释放资源
		session.close();
	}

	@Override
	public void delete(long id) {
		//调取工具类获取连接
		SqlSession session = UserDAOUtil.openSession();
		
		//执行SQL
		session.insert("cn.wolfcode.mybatis.mapper.UserMapper.delete",id);
		
		//提交事务
		session.commit();
		
		//释放资源
		session.close();
	}

	@Override
	public void update(User u) {
		//调取工具类获取连接
		SqlSession session = UserDAOUtil.openSession();
		
		//执行SQL
		session.update("cn.wolfcode.mybatis.mapper.UserMapper.update",u);
		
		//提交事务
		session.commit();
		
		//释放资源
		session.close();
		
	}

	@Override
	public User selectOne(long id) {
		//调取工具类获取连接
		SqlSession session = UserDAOUtil.openSession();
		
		//执行SQL
		User u = session.selectOne("cn.wolfcode.mybatis.mapper.UserMapper.selectOne",id);
		
		//释放资源
		session.close();
		
		//返回结果
		return u;
	}

	@Override
	public List<User> selectAll() {
		//调取工具类获取连接
		SqlSession session = UserDAOUtil.openSession();
		
		//执行SQL
		List<User> list= session.selectList("cn.wolfcode.mybatis.mapper.UserMapper.selectAll");
		
		//释放资源
		session.close();
		
		//返回结果
		return list;
	}
}
