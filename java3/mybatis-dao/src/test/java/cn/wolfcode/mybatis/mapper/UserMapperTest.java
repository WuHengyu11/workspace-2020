package cn.wolfcode.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import cn.wolfcode.mybatis.domain.User;
import cn.wolfcode.mybatis.util.MyBatisUtil;

public class UserMapperTest {
	@Test
	public void testSave() throws Exception {
		//创建对象
		User u = new User();
		//设置值
		u.setUsername("张三");
		u.setPassword("123456");
		//获取连接
		SqlSession session = MyBatisUtil.getConnection();
		session.insert("cn.wolfcode.mybatis.mapper.UserMapper.save", u);
		session.commit();
		session.close();
		System.out.println();
	}
	
	@Test
	public void testSave1() throws Exception {
		//创建对象
		User u = new User();
		//设置值
		u.setUsername("关羽");
		u.setPassword("123456");
		//获取连接
		SqlSession session = MyBatisUtil.getConnection();
		UserMapper mapper = session.getMapper(UserMapper.class);
		mapper.save(u);
		session.commit();
		session.close();
		System.out.println(u);
	}
	
	@Test
	public void testDelete() throws Exception {
		//获取连接
		SqlSession session = MyBatisUtil.getConnection();
		session.insert("cn.wolfcode.mybatis.mapper.UserMapper.delete", 1L);
		session.commit();
		session.close();
	}
	
	@Test
	public void testDelete1() throws Exception {
		//获取连接
		SqlSession session = MyBatisUtil.getConnection();
		UserMapper mapper = session.getMapper(UserMapper.class);
		mapper.delete(2L);
		session.commit();
		session.close();
		System.out.println();
	}
	
	@Test
	public void testGet() throws Exception {
		SqlSession session = MyBatisUtil.getConnection();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.get(2L);
		session.close();
		System.out.println(user);
	}
	
	@Test
	public void testSelectAll() throws Exception {
		SqlSession session = MyBatisUtil.getConnection();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<User> userList = mapper.selectAll();
		for (User user : userList) {
			System.out.println(user);
		}
		session.close();
	}
	
	@Test
	public void testLogin() throws Exception {
		SqlSession session = MyBatisUtil.getConnection();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User u = mapper.login("张三", "123456");
		System.out.println(u);
	}
}
