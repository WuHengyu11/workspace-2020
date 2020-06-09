package cn.wolfcode.javaweb.service;

import java.util.List;

import cn.wolfcode.javaweb.domain.Product;

public interface IProductService {
	
	void save(Product p);
	void delete(long id);
	void update(Product p);
	Product get(long id);
	List<Product> listAll();
}
