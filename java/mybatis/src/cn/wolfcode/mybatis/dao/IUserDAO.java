package cn.wolfcode.mybatis.dao;

import java.util.List;

import cn.wolfcode.mybatis.domain.User;

/**
* @author 作者: 邬恒宇
* @version 创建时间：2020年2月28日 下午9:22:16
*/
public interface IUserDAO {
	void insert(User u);
	void delete(long id);
	void update(User u);
	User selectOne(long id);
	List<User> selectAll();
}
