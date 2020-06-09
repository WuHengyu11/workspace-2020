package cn.wolfcode.managesystem.test;

import java.math.BigDecimal;
import org.junit.Test;

import cn.wolfcode.managesystem.dao.IEmployeeDAO;
import cn.wolfcode.managesystem.dao.impl.EmployeeDAOImpl;
import cn.wolfcode.managesystem.domain.Employee;

public class EmployeeDAOTest {
	//关联对象
	IEmployeeDAO dao = new EmployeeDAOImpl();
	
	@Test
	public void testInsert() {
		Employee e = new Employee(null,"张三","123456@123.com",30,new BigDecimal(2000),null,"RL");
		dao.insert(e);
	}

	@Test
	public void testDelete() {
		dao.delete(4L);
	}

	@Test
	public void testUpdate() {
		Employee e = new Employee(7L,"勒布朗詹姆斯","123456@123.com",30,new BigDecimal(2000),null,"RL");
		dao.update(e);
	}

	@Test
	public void testSelectOne() {
		Employee selectOne = dao.selectOne(7L);
		System.out.println(selectOne);
	}

}
