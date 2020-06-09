package cn.wolfcode.javaweb2.util;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybaitsUtil {
	private static SqlSessionFactory ssf;	
	
	/**
	 * 用于获取数据库信息
	 */
	static{
		try {
			ssf = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 用于获取并返回连接
	 * @return 连接信息
	 */
	public static SqlSession getConnection(){
		return ssf.openSession();
	}
}
