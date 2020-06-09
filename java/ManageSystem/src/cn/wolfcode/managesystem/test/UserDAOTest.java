package cn.wolfcode.managesystem.test;


import org.junit.Test;

import cn.wolfcode.managesystem.dao.IUserDAO;
import cn.wolfcode.managesystem.dao.impl.UserDAOImpl;
import cn.wolfcode.managesystem.domain.User;

public class UserDAOTest {
	IUserDAO dao = new UserDAOImpl();
	@Test
	public void testSelectByUsernameAndPassword() {
		String username = "admin";
		String password = "1";
		User selectByUsernameAndPassword = dao.selectByUsernameAndPassword(username, password);
		System.out.println(selectByUsernameAndPassword);
	}

}
