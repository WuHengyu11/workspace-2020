package cn.wolfcode.mybatis.mapper;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import cn.wolfcode.mybatis.domain.Teacher;
import cn.wolfcode.mybatis.domain.Student;
import cn.wolfcode.mybatis.mapper.TeacherMapper;
import cn.wolfcode.mybatis.mapper.StudentMapper;
import cn.wolfcode.mybatis.util.MybatisUtil;

public class StudentMapperTest {
	@Test
	public void testSave() throws Exception {
		SqlSession session = MybatisUtil.getConnection();
		TeacherMapper t = session.getMapper(TeacherMapper.class);
		StudentMapper s = session.getMapper(StudentMapper.class);
		
		Teacher teacher1 = new Teacher();
		teacher1.setName("王老师");
		Teacher teacher2 = new Teacher();
		teacher2.setName("李老师");
		
		Student student1 = new Student();
		student1.setName("阿狸");
		//维护关系
		student1.getTeachers().add(teacher1);
		student1.getTeachers().add(teacher2);
		
		Student student2 = new Student();
		student2.setName("闪电");
		student2.getTeachers().add(teacher1);
		student2.getTeachers().add(teacher2);
		t.save(teacher1);
		t.save(teacher2);
		s.save(student1);
		s.save(student2);
		
		//保存数据到中间表
		for (Teacher t1 : student1.getTeachers()) {
			s.insertRelationWithTeacher(student1.getId(), t1.getId());
		}
		for (Teacher t1 : student2.getTeachers()) {
			s.insertRelationWithTeacher(student2.getId(), t1.getId());
		}
		session.commit();
		session.close();
	}
	@Test
	public void testGet() throws Exception {
		SqlSession session = MybatisUtil.getConnection();
		StudentMapper s = session.getMapper(StudentMapper.class);
		Student student = s.get(1L);
		System.out.println(student);
		System.out.println(student.getTeachers());
		session.close();
	}
	@Test
	public void testDelete() throws Exception {
		SqlSession session = MybatisUtil.getConnection();
		StudentMapper s = session.getMapper(StudentMapper.class);
		s.delete(1L);
		s.deleteRelation(1L);
		session.commit();
		session.close();
	}
}
