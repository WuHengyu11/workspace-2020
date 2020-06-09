package cn.wolfcode.employeetest.service.impl;

import java.util.List;

import cn.wolfcode.employeetest.dao.IEmployeeDAO;
import cn.wolfcode.employeetest.dao.impl.EmployeeDAOImpl;
import cn.wolfcode.employeetest.domain.Employee;
import cn.wolfcode.employeetest.service.IEmployeeService;


public class EmployeeServiceImpl implements IEmployeeService {
	
	//关联对象
	IEmployeeDAO dao = new EmployeeDAOImpl();
		
	@Override
	public void save(Employee e) {
		dao.insert(e);

	}

	@Override
	public void delete(long id) {
		dao.delete(id);

	}

	@Override
	public void update(Employee e) {
		dao.update(e);

	}

	@Override
	public Employee get(long id) {
		Employee e = dao.selectOne(id);
		return e;
	}

	@Override
	public List<Employee> listAll() {
		List<Employee> employeeList = dao.selectAll();
		return employeeList;
	}

}
