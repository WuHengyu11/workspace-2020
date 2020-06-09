package cn.wolfcode.mybatis2.util;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
* @author 作者: 邬恒宇
* @version 创建时间：2020年2月28日 下午9:57:25
*/
public class StudentDAOUtil {
	private static SqlSessionFactory fac;
	static{
		try {
			fac = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SqlSession openSession(){
		return fac.openSession();
	}
}
