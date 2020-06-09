package cn.wolfcode.managesystem.dao;

import cn.wolfcode.managesystem.domain.User;

public interface IUserDAO {
	
	/**
	 * 登录查询
	 * @param username
	 * @param password
	 * @return
	 */
	User selectByUsernameAndPassword(String username,String password);

	User selectByUsername(String username);

	void insert(User u);
}
