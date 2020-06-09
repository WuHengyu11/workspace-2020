package cn.wolfcode.mybatis.util;
/**
* @author 作者: 邬恒宇
* @version 创建时间：2020年2月28日 下午4:53:18
*/

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class UserDAOUtil {
	
	private UserDAOUtil(){}
	private static SqlSessionFactory fac;
	static{
		try {
			fac = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//获取SqlSession
	public static SqlSession openSession(){
		return fac.openSession();
	}
}
