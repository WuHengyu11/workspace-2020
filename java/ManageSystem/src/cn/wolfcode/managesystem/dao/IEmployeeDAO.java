package cn.wolfcode.managesystem.dao;

import java.util.List;

import cn.wolfcode.managesystem.domain.Employee;
import cn.wolfcode.managesystem.query.QueryObject;

public interface IEmployeeDAO {
	/**
	 * 插入数据
	 * @param dt
	 */
	void insert(Employee e);
	
	/**
	 * 删除数据
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * 更新数据
	 * @param dt
	 */
	void update(Employee e);
	
	/**
	 * 查询单挑数据
	 * @param id
	 * @return
	 */
	Employee selectOne(Long id);
	
	/**
	 * 用于查询总条数
	 * @param qo
	 * @return
	 */
	int queryForCount(QueryObject qo);
	
	/**
	 * 用于返回查询的结果集
	 * @param qo
	 * @return
	 */
	List<?> queryList(QueryObject qo);
}
