package cn.wolfcode.managesystem.service.impl;

import java.util.List;

import cn.wolfcode.managesystem.dao.IEmployeeDAO;
import cn.wolfcode.managesystem.dao.impl.EmployeeDAOImpl;
import cn.wolfcode.managesystem.domain.Employee;
import cn.wolfcode.managesystem.query.PageResult;
import cn.wolfcode.managesystem.query.QueryObject;
import cn.wolfcode.managesystem.service.IEmployeeService;

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
	public PageResult query(QueryObject qo) {
		int rows = dao.queryForCount(qo);
		
		if (rows == 0){
			return PageResult.empty(qo.getPageSize());
		}
		
		List<?> result = dao.queryList(qo);
		return new PageResult(rows, result, qo.getCurrentPage(), qo.getPageSize());
	}

}
