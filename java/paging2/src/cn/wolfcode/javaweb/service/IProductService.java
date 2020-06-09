package cn.wolfcode.javaweb.service;

import java.util.List;

import cn.wolfcode.javaweb.domain.Product;
import cn.wolfcode.javaweb.query.PageResult;
import cn.wolfcode.javaweb.query.QueryObject;

public interface IProductService {
	
	void save(Product p);
	void delete(long id);
	void update(Product p);
	Product get(long id);
	List<Product> listAll();
	
	PageResult query(QueryObject qo);
}
