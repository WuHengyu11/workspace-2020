package cn.wolfcode.javaweb2.dao;

import java.util.List;

import cn.wolfcode.javaweb2.domain.Employee;

public interface IEmployeeDAO {
	
	/**
	 * 插入数据
	 * @param e
	 */
	void insert(Employee e);
	
	/**
	 * 删除数据
	 * @param id
	 */
	void delete(long id);
	
	/**
	 * 更新数据
	 * @param e
	 */
	void update(Employee e);
	
	/**
	 * 查询单条数据
	 * @param id
	 * @return Employee
	 */
	Employee selectOne(long id);
	
	/**
	 * 查询所有数据
	 * @return List<Employee>
	 */
	List<Employee> selectAll();
	
}
