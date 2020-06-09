package cn.wolfcode.javaweb.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import cn.wolfcode.javaweb.dao.IProductDAO;
import cn.wolfcode.javaweb.domain.Product;
import cn.wolfcode.javaweb.impl.ProductDAOImpl;
import cn.wolfcode.javaweb.query.ProductQueryObject;
import cn.wolfcode.javaweb.query.QueryObject;

public class ProductDAOTest {
	private IProductDAO dao = new ProductDAOImpl();
	@Test
	public void testInsert() {
		Product pt = new Product(null,"卫生纸",10L,200,"美国","花王",0.1,300);
		dao.insert(pt);
	}

	@Test
	public void testDelete() {
		dao.delete(6L);
	}

	@Test
	public void testUpdate() {
		Product pt =  new Product(30L,"卫生纸",10L,200,"日本","花王",0.1,300);
		dao.update(pt);
	}

	@Test
	public void testSelectOne() {
		Product pt = dao.selectOne(30L);
		System.out.println(pt);
	}

	@Test
	public void testSelectAll() {
		List<Product> productList = dao.selectAll();
		for (Product product : productList) {
			System.out.println(product);
		}
	}
	
	@Test
	public void testQuery() throws Exception {
		QueryObject qo = new QueryObject();
		qo.setCurrentPage(2);
		qo.setPageSize(5);
		List<?> queryList = dao.queryList(qo);
		for (Object object : queryList) {
			System.out.println(object);
		}
	}
	
	@Test
	public void testQuery1() throws Exception {
		ProductQueryObject qo = new ProductQueryObject();
		qo.setMinPrice(new BigDecimal(10));
		qo.setMaxPrice(new BigDecimal(120));
		
		List<?> queryList = dao.queryList(qo);
		for (Object object : queryList) {
			System.out.println(object);
		}
	}

}
