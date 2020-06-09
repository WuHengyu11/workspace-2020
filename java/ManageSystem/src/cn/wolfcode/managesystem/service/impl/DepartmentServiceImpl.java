package cn.wolfcode.managesystem.service.impl;

import java.util.List;

import cn.wolfcode.managesystem.dao.IDepartmentDAO;
import cn.wolfcode.managesystem.dao.impl.DepartmentDAOImpl;
import cn.wolfcode.managesystem.domain.Department;
import cn.wolfcode.managesystem.query.PageResult;
import cn.wolfcode.managesystem.query.QueryObject;
import cn.wolfcode.managesystem.service.IDepartmentService;

public class DepartmentServiceImpl implements IDepartmentService {
	//关联对象
	IDepartmentDAO dao = new DepartmentDAOImpl();
	
	@Override
	public void save(Department dt) {
		dao.insert(dt);
	}

	@Override
	public void delete(long id) {
		dao.delete(id);
	}

	@Override
	public void update(Department dt) {
		dao.update(dt);
	}

	@Override
	public Department get(long id) {
		Department dt = dao.selectOne(id);
		return dt;
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
