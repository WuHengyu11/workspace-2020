package cn.wolfcode.employeetest.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import cn.wolfcode.employeetest.dao.IEmployeeDAO;
import cn.wolfcode.employeetest.dao.impl.EmployeeDAOImpl;
import cn.wolfcode.employeetest.domain.Employee;



public class EmployeeDAOTest {

	//关联对象
	IEmployeeDAO dao = new EmployeeDAOImpl();
	
	@Test
	public void testInsert() {
		Employee e = new Employee(null,"安东尼戴维斯","123456","123456@163.com",35,new BigDecimal(2500));
		dao.insert(e);
	}

	@Test
	public void testDelete() {
		dao.delete(20L);
	}

	@Test
	public void testUpdate() {
		Employee e = new Employee(7L,"安东尼戴维斯","123456","123456@163.com",35,new BigDecimal(2500));
		dao.update(e);
	}

	@Test
	public void testSelectOne() {
		Employee e = dao.selectOne(7L);
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
