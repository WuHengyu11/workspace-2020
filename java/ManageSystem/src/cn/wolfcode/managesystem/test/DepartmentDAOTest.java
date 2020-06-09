package cn.wolfcode.managesystem.test;

import java.util.List;

import org.junit.Test;

import cn.wolfcode.managesystem.dao.IDepartmentDAO;
import cn.wolfcode.managesystem.dao.impl.DepartmentDAOImpl;
import cn.wolfcode.managesystem.domain.Department;
import cn.wolfcode.managesystem.query.QueryObject;

public class DepartmentDAOTest {
	IDepartmentDAO dao = new DepartmentDAOImpl();
	@Test
	public void testInsert() {
		Department dt = new Department(null,"保卫部","BW");
		dao.insert(dt);
	}

	@Test
	public void testDelete() {
		dao.delete(2L);
	}

	@Test
	public void testUpdate() {
		Department dt = new Department(3L,"人力资源部","RL");
		dao.update(dt);
	}

	@Test
	public void testSelectOne() {
		Department selectOne = dao.selectOne(3L);
		System.out.println(selectOne);
	}

	@Test
	public void testSelectForCount() {
		QueryObject qo = new QueryObject();
		int queryForCount = dao.queryForCount(qo);
		System.out.println(queryForCount);
	}
	
	@Test
	public void testQueryForList() {
		QueryObject qo = new QueryObject();
		List<?> queryList = dao.queryList(qo);
		for (Object object : queryList) {
			System.out.println(object);
		}
		
	}
}
