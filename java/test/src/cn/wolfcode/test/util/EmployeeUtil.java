package cn.wolfcode.test.util;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class EmployeeUtil {
	private static SqlSessionFactory ssf;
	
	//获取链接
	static{
		try {
			ssf = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//返回链接
	public static SqlSession getConnection(){
		return ssf.openSession();
		
	}

}
