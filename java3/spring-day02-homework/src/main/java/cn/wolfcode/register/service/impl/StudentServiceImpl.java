package cn.wolfcode.register.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.register.dao.IStudentDAO;
import cn.wolfcode.register.service.IStudentService;
@Service
public class StudentServiceImpl implements IStudentService {
	@Autowired
	private IStudentDAO studentDAO;
	
	@Override
	public void register(String username, String password) throws SQLException {
		studentDAO.register(username, password);
	}

}
