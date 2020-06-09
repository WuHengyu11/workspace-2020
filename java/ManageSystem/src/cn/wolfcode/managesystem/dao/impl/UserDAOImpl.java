package cn.wolfcode.managesystem.dao.impl;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import cn.wolfcode.managesystem.dao.IUserDAO;
import cn.wolfcode.managesystem.domain.User;
import cn.wolfcode.managesystem.util.MyBatisUtil;

public class UserDAOImpl implements IUserDAO{

	@Override
	public User selectByUsernameAndPassword(String username, String password) {
		//获取连接
		SqlSession session = MyBatisUtil.getConnection();
		
		//将账号和密码进行封装
		HashMap<String, Object> map = new HashMap<>();
		map.put("username", username);
		map.put("password", password);
		//执行sql
		User u = session.selectOne("cn.wolfcode.managesystem.mapper.UserMapper.selectByUsernameAndPassword",map);
		session.close();
		return u;
	}

	@Override
	public User selectByUsername(String username) {
		//获取连接
		SqlSession session = MyBatisUtil.getConnection();
		User u = session.selectOne("cn.wolfcode.managesystem.mapper.UserMapper.selectByUsername",username);
		session.close();
		return u;
	}

	@Override
	public void insert(User u) {
		//获取连接
		SqlSession session = MyBatisUtil.getConnection();
		session.insert("cn.wolfcode.managesystem.mapper.UserMapper.insert",u);
		session.commit();
		session.close();
	}

}
