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
	 * 
	 * @return
	 */
	int selectForCount(QueryObject qo);
	
	List<Product> selectFotList(QueryObject qo);
}
