package cn.wolfcode.mybatis2.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.wolfcode.mybatis2.dao.IStudentDAO;
import cn.wolfcode.mybatis2.domain.Student;
import cn.wolfcode.mybatis2.util.StudentDAOUtil;

/**
* @author 作者: 邬恒宇
* @version 创建时间：2020年2月28日 下午9:56:35
*/
public class StudentDAOImpl implements IStudentDAO {

	@Override
	public void insert(Student s) {
		//获取连接
		SqlSession sn = StudentDAOUtil.openSession();
		
		//执行SQL语句
		sn.insert("cn.wolfcode.mybatis2.mapper.StudentMapper.insert",s);
		
		//执行事务
		sn.commit();
		
		//释放资源
		sn.close();;
	}

	@Override
	public void delete(long id) {
		//获取连接
		SqlSession sn = StudentDAOUtil.openSession();
		
		//执行SQL语句
		sn.delete("cn.wolfcode.mybatis2.mapper.StudentMapper.delete",id);
		
		//执行事务
		sn.commit();
		
		//释放资源
		sn.close();;
	}

	@Override
	public void update(Student s) {
		//获取连接
		SqlSession sn = StudentDAOUtil.openSession();
		
		//执行SQL语句
		sn.update("cn.wolfcode.mybatis2.mapper.StudentMapper.update",s);
		
		//执行事务
		sn.commit();
		
		//释放资源
		sn.close();;
	}

	@Override
	public Student selectOne(long id) {
		//获取连接
		SqlSession sn = StudentDAOUtil.openSession();
		
		//执行SQL语句
		Student s = sn.selectOne("cn.wolfcode.mybatis2.mapper.StudentMapper.selectOne",id);
		
		//执行事务
		sn.commit();
		
		//释放资源
		sn.close();
		
		return s;
	}

	@Override
	public List<Student> selectAll() {
		//获取连接
		SqlSession sn = StudentDAOUtil.openSession();
		
		//执行SQL语句
		List<Student> list = sn.selectList("cn.wolfcode.mybatis2.mapper.StudentMapper.selectAll");
		
		//执行事务
		sn.commit();
		
		//释放资源
		sn.close();
		return list;
	}

}
