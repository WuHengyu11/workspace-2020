package cn.wolfcode.managesystem.dao;

import java.util.List;


import cn.wolfcode.managesystem.domain.Department;
import cn.wolfcode.managesystem.query.QueryObject;

public interface IDepartmentDAO {
	/**
	 * 插入数据
	 * @param dt
	 */
	void insert(Department dt);
	
	/**
	 * 删除数据
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * 更新数据
	 * @param dt
	 */
	void update(Department dt);
	
	/**
	 * 查询单条数据
	 * @param id
	 * @return
	 */
	Department selectOne(Long id);
	
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
