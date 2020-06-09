package cn.wolfcode.mybatis.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.wolfcode.mybatis.dao.IUserDAO;
import cn.wolfcode.mybatis.domain.User;
import cn.wolfcode.mybatis.impl.UserDAOImpl;

/**
* @author 作者: 邬恒宇
* @version 创建时间：2020年2月28日 下午9:34:30
*/
public class UserDAOTest {
	//获取关联对象
	private IUserDAO dao = new UserDAOImpl();
	
	@Test
	public void testInsert() {
		User u = new User(null, "张三", 20, new BigDecimal(20), new Date());
		dao.insert(u);
	}

	@Test
	public void testDelete() {
		dao.delete(3L);
	}

	@Test
	public void testUpdate() {
		User u = new User(4L, "四", 20, new BigDecimal(20), new Date());
		dao.update(u);
	}

	@Test
	public void testSelectOne() {
		User u = dao.selectOne(4L);
		System.out.println(u);
	}

	@Test
	public void testSelectAll() {
		List<User> list = dao.selectAll();
		for (User user : list) {
			System.out.println(user);
		}
	}

}
