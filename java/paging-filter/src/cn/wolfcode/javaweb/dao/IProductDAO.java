package cn.wolfcode.javaweb.dao;

import java.util.List;

import cn.wolfcode.javaweb.domain.Product;
import cn.wolfcode.javaweb.query.QueryObject;

public interface IProductDAO {
	void insert(Product pt);
	void delete(long id);
	void update(Product pt);
	Product selectOne(long id);
	List<Product> selectAll();
	
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
