package cn.wolfcode.managesystem.service;

import cn.wolfcode.managesystem.domain.Employee;
import cn.wolfcode.managesystem.query.PageResult;
import cn.wolfcode.managesystem.query.QueryObject;

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
	
	PageResult query(QueryObject qo);
}
