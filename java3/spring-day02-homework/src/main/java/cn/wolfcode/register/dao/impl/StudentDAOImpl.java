package cn.wolfcode.register.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.wolfcode.register.dao.IStudentDAO;
import lombok.Cleanup;
@Repository
public class StudentDAOImpl implements IStudentDAO {
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void register(String username, String password) throws SQLException {
		//使用JDBC连接数据库
		@Cleanup
		Connection connection = dataSource.getConnection();
		@Cleanup
		PreparedStatement ps = connection.prepareStatement("INSERT INTO student(username,password) VALUES (?,?)");
		ps.setString(1, username);
		ps.setString(2, password);
		ps.executeUpdate();
	}

}
