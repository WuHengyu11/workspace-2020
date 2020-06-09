package cn.wolfcode.mybatis.util;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	private static SqlSessionFactory ssf;
	static{
		try {
			ssf = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("Mybatis-Config.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SqlSession getConnection(){
		return ssf.openSession();
	}
}
