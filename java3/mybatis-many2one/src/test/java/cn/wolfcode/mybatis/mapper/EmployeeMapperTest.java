package cn.wolfcode.mybatis.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import cn.wolfcode.mybatis.domain.Department;
import cn.wolfcode.mybatis.domain.Employee;
import cn.wolfcode.mybatis.mapper.DepartmentMapper;
import cn.wolfcode.mybatis.mapper.EmployeeMapper;
import cn.wolfcode.mybatis.util.MybatisUtil;

public class EmployeeMapperTest {
	@Test
	public void testSave() throws Exception {
		SqlSession session = MybatisUtil.getConnection();
		EmployeeMapper e = session.getMapper(EmployeeMapper.class);
		DepartmentMapper d = session.getMapper(DepartmentMapper.class);
		//先保存部门
		Department dept = new Department();
		dept.setName("财务部");
		d.save(dept);
		
		//后保存员工对象
		Employee e1 = new Employee();
		e1.setName("武松");
		e1.setDept(dept);
		e.save(e1);
		
		Employee e2 = new Employee();
		e2.setName("武值");
		e2.setDept(dept);
		e.save(e2);
		session.commit();
		session.close();
	}
	@Test
	public void testGet() throws Exception {
		SqlSession session = MybatisUtil.getConnection();
		EmployeeMapper e = session.getMapper(EmployeeMapper.class);
		Employee employee = e.get(10L);
		System.out.println(employee);
		System.out.println(employee.getDept());
	}
}
