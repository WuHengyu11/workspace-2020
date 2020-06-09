package cn.wolfcode.test.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import cn.wolfcode.test.dao.IEmployeeDAO;
import cn.wolfcode.test.domain.Employee;
import cn.wolfcode.test.impl.EmployeeDAOImpl;

public class EmployeeDAOTest {
	//关联对象
	IEmployeeDAO  dao = new EmployeeDAOImpl();

	@Test
	public void testInsert() {
		Employee ee = new Employee(null, "库里", "123456@163.com", 30, new BigDecimal(20000));
		dao.insert(ee);
	}

	@Test
	public void testDelete() {
		dao.delete(1L);
	}

	@Test
	public void testUpdate() {
		Employee ee = new Employee(2L, "张飞", "123456@163.com", 30, new BigDecimal(20000));
		dao.update(ee);
	}

	@Test
	public void testSelectOne() {
		Employee ee = dao.selectOne(2L);
		System.out.println(ee);
	}

	@Test
	public void testSelectAll() {
		List<Employee> list = dao.selectAll();
		for (Employee e : list) {
			System.out.println(e);
		}
	}

}
