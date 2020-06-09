package cn.wolfcode.register.service.impl;

import java.sql.SQLException;

import cn.wolfcode.register.dao.IStudentDAO;
import cn.wolfcode.register.service.IStudentService;

public class StudentServiceImpl implements IStudentService {
	private IStudentDAO studentDAO;
	public void setStudentDAO(IStudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	@Override
	public void register(String username, String password) throws SQLException {
		studentDAO.register(username, password);
	}

}
