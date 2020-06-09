package cn.wolfcode.javaweb2.service;

import java.util.List;

import cn.wolfcode.javaweb2.domain.Employee;


public interface IEmployeeService {
	/**
	 * 数据保存服务
	 * @param e
	 */
	void save(Employee e);
	
	/**
	 * 数据删除服务
	 * @param id
	 */
	void delete(long id);
	
	/**
	 * 数据更新服务
	 * @param e
	 */
	void update(Employee e);
	
	/**
	 * 数据查询服务
	 * @param id
	 * @return Employee
	 */
	Employee get(long id);
	
	/**
	 * 数据查询服务
	 * @return List<Employee>
	 */
	List<Employee> listAll();
}
