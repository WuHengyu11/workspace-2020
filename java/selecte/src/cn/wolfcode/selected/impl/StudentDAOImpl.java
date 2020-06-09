package cn.wolfcode.selected.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.wolfcode.selected.dao.IStudentDAO;
import cn.wolfcode.selected.domain.Student;
import cn.wolfcode.selected.util.StudentUtil;

public class StudentDAOImpl implements IStudentDAO {

	@Override
	public List<Student> studentList() {
		
		//获取链接
		SqlSession session = StudentUtil.getConnection();	
		
		//执行sql语句
		List<Student> list = session.selectList("cn.wolfcode.selected.mapper.StudentMapper.selectAll");
		
		//释放资源
		session.close();
		
		return list;
	}

}
