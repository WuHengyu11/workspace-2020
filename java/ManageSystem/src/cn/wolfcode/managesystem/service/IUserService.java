package cn.wolfcode.managesystem.service;

import cn.wolfcode.managesystem.domain.User;

public interface IUserService {
	User login(String username,String password);

	User getByUserName(String username);

	void save(User u);
}
