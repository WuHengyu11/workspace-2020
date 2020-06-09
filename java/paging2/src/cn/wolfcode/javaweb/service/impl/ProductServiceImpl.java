package cn.wolfcode.javaweb.service.impl;

import java.util.List;

import cn.wolfcode.javaweb.dao.IProductDAO;
import cn.wolfcode.javaweb.domain.Product;
import cn.wolfcode.javaweb.impl.ProductDAOImpl;
import cn.wolfcode.javaweb.query.PageResult;
import cn.wolfcode.javaweb.query.QueryObject;
import cn.wolfcode.javaweb.service.IProductService;

public class ProductServiceImpl implements IProductService {

	//关联对象
	private IProductDAO dao = new ProductDAOImpl();
	@Override
	public void save(Product p) {
		dao.insert(p);
	}

	@Override
	public void delete(long id) {
		dao.delete(id);
	}

	@Override
	public void update(Product p) {
		dao.update(p);
	}

	@Override
	public Product get(long id) {
		return dao.selectOne(id);
	}

	@Override
	public List<Product> listAll() {
		return dao.selectAll();
	}

	@Override
	public PageResult query(QueryObject qo) {
		int rows = dao.queryForCount(qo);
		
		if (rows == 0){
			return PageResult.empty(qo.getPageSize());
		}
		
		List<?> result = dao.queryList(qo);
		return new PageResult(rows, result, qo.getCurrentPage(), qo.getPageSize());
	}

}
