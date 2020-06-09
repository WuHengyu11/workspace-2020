package cn.wolfcode.mybatis2.dao;

import java.util.List;

import cn.wolfcode.mybatis2.domain.Student;

/**
* @author 作者: 邬恒宇
* @version 创建时间：2020年2月28日 下午9:48:47
*/
public interface IStudentDAO {
	
	//定义插入方法
	void insert(Student s);
	
	//定义删除方法
	void delete(long id);
	
	//定义更新方法
	void update(Student s);
	
	//定义单个查询方法
	Student selectOne(long id);
	
	//定义查询所有方法
	List<Student> selectAll();
}
