package cn.wolfcode.test.dao;

import java.util.List;

import cn.wolfcode.test.domain.Employee;

public interface IEmployeeDAO {
	
	void insert(Employee ee);
	
	void delete(long id);
	
	void update(Employee ee);
	
	Employee selectOne(long id);
	
	List<Employee> selectAll();
	
}
