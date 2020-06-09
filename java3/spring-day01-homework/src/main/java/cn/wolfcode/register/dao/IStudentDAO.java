package cn.wolfcode.register.dao;

import java.sql.SQLException;

public interface IStudentDAO {
	void register(String username,String password) throws SQLException;
}
