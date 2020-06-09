package cn.wolfcode.managesystem.service.impl;

import cn.wolfcode.managesystem.dao.IUserDAO;
import cn.wolfcode.managesystem.dao.impl.UserDAOImpl;
import cn.wolfcode.managesystem.domain.User;
import cn.wolfcode.managesystem.service.IUserService;

public class UserServiceImpl implements IUserService {
	//关联对象
	private IUserDAO dao = new UserDAOImpl();
	@Override
	public User login(String username, String password) {
		return dao.selectByUsernameAndPassword(username, password);
	}
	@Override
	public User getByUserName(String username) {
		return dao.selectByUsername(username);
	}
	@Override
	public void save(User u) {
		dao.insert(u);
		
	}

}
