package cn.wolfcode.managesystem.service;

import cn.wolfcode.managesystem.domain.Department;
import cn.wolfcode.managesystem.query.PageResult;
import cn.wolfcode.managesystem.query.QueryObject;

public interface IDepartmentService {
	/**
	 * 数据保存服务
	 * @param e
	 */
	void save(Department dt);
	
	/**
	 * 数据删除服务
	 * @param id
	 */
	void delete(long id);
	
	/**
	 * 数据更新服务
	 * @param e
	 */
	void update(Department e);
	
	/**
	 * 数据查询服务
	 * @param id
	 * @return Employee
	 */
	Department get(long id);
	
	
	PageResult query(QueryObject qo);
}
