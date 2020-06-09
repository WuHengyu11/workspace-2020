package cn.wolfcode.javaweb2.service.impl;

import java.util.List;

import cn.wolfcode.javaweb2.dao.IEmployeeDAO;
import cn.wolfcode.javaweb2.dao.impl.EmployeeDAOImpl;
import cn.wolfcode.javaweb2.domain.Employee;
import cn.wolfcode.javaweb2.service.IEmployeeService;

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
