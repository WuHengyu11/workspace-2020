package cn.wolfcode.register.service;

import java.sql.SQLException;

public interface IStudentService {
	void register(String username,String password) throws SQLException;
}
