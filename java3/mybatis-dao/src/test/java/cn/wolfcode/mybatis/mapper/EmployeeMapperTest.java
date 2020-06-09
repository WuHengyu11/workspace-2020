package cn.wolfcode.mybatis.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import cn.wolfcode.mybatis.domain.Employee;
import cn.wolfcode.mybatis.util.MyBatisUtil;

public class EmployeeMapperTest {
	
	@Test
	public void testGet() throws Exception {
		SqlSession session = MyBatisUtil.getConnection();
		EmployeeMapper mapper= session.getMapper(EmployeeMapper.class);
		Employee e = mapper.get(1L);
		System.out.println(e);
	}
	
	@Test
	public void testSelectAll() throws Exception {
		SqlSession session = MyBatisUtil.getConnection();
		EmployeeMapper mapper= session.getMapper(EmployeeMapper.class);
		List<Employee> list = mapper.selectAll();
		for (Employee employee : list) {
			System.out.println(employee);	
		}
	}
	
	@Test
	public void testQueryByMinSalaryAndMaxSalary() throws Exception {
		SqlSession session = MyBatisUtil.getConnection();
		EmployeeMapper mapper= session.getMapper(EmployeeMapper.class);
		List<Employee> e = mapper.queryByMinSalaryAndMaxSalary(new BigDecimal(800), new BigDecimal(1200));
		for (Employee employee : e) {
			System.out.println(employee);
		}
	}
	
	@Test
	public void testUpdate() throws Exception {
		Employee e = new Employee();
		e.setId(1L);
		e.setName("张飞");
		e.setSn("001");
		e.setSalary(new BigDecimal(3000));
		e.setDeptid(003L);
		SqlSession session = MyBatisUtil.getConnection();
		EmployeeMapper mapper= session.getMapper(EmployeeMapper.class);
		mapper.update(e);
		session.commit();
		session.close();
	}
	
	@Test
	public void testDelete() throws Exception {
		SqlSession session = MyBatisUtil.getConnection();
		EmployeeMapper mapper= session.getMapper(EmployeeMapper.class);
		mapper.delete(new Long[] {1L,2L,3L});
		session.commit();
		session.close();
	}
}
