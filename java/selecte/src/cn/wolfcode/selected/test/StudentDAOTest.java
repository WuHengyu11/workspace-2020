package cn.wolfcode.selected.test;

import java.util.List;

import org.junit.Test;

import cn.wolfcode.selected.dao.IStudentDAO;
import cn.wolfcode.selected.domain.Student;
import cn.wolfcode.selected.impl.StudentDAOImpl;

public class StudentDAOTest {
	//创建关联对象
	IStudentDAO dao = new StudentDAOImpl();
	@Test
	public void testStudentList() {
		List<Student> studentList = dao.studentList();
		for (Student student : studentList) {
			System.out.println(student);
		}
	}

}
