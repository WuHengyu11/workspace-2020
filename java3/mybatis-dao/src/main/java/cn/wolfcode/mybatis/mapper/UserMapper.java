package cn.wolfcode.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.wolfcode.mybatis.domain.User;

public interface UserMapper {
	void save(User u);
	void update(Long id);
	void delete(Long id);
	User get(Long id);
	List<User> selectAll();
	User login(@Param("username")String username,@Param("password")String password);
}
