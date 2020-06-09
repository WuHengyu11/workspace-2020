package cn.wolfcode.javaweb2.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.wolfcode.javaweb2.dao.IEmployeeDAO;
import cn.wolfcode.javaweb2.dao.impl.EmployeeDAOImpl;
import cn.wolfcode.javaweb2.domain.Employee;

public class EmployeeDAOTest {

	//关联对象
	IEmployeeDAO dao = new EmployeeDAOImpl();
	
	@Test
	public void testInsert() {
		Employee e = new Employee(null,"安东尼戴维斯","123456@163.com",36,new BigDecimal(2500),new Date());
		dao.insert(e);
	}

	@Test
	public void testDelete() {
		dao.delete(1L);
	}

	@Test
	public void testUpdate() {
		Employee e = new Employee(3L,"库兹马","123456@163.com",36,new BigDecimal(2500),new Date());
		dao.update(e);
	}

	@Test
	public void testSelectOne() {
		Employee e = dao.selectOne(2L);
		System.out.println(e);
	}

	@Test
	public void testSelectAll() {
		List<Employee> employeeList = dao.selectAll();
		for (Employee employee : employeeList) {
			System.out.println(employee);
		}
	}

}
