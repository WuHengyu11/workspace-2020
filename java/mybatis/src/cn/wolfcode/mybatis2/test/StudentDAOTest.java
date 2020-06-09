package cn.wolfcode.mybatis2.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.wolfcode.mybatis2.dao.IStudentDAO;
import cn.wolfcode.mybatis2.domain.Student;
import cn.wolfcode.mybatis2.impl.StudentDAOImpl;

/**
* @author 作者: 邬恒宇
* @version 创建时间：2020年2月28日 下午10:04:20
*/
public class StudentDAOTest {
	//关联对象
	private IStudentDAO dao= new StudentDAOImpl();
	@Test
	public void testInsert() {
		Student s = new Student(null, "欧文", 35);
		dao.insert(s);
	}

	@Test
	public void testDelete() {
		dao.delete(2L);
	}

	@Test
	public void testUpdate() {
		Student s = new Student(1L, "current", 35);
		dao.update(s);
	}

	@Test
	public void testSelectOne() {
		Student s = dao.selectOne(1L);
		System.out.println(s);
	}

	@Test
	public void testSelectAll() {
		List<Student> list = dao.selectAll();
		for (Student student : list) {
			System.out.println(student);
		}
	}

}
