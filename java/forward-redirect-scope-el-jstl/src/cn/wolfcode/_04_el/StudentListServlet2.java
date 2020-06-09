package cn.wolfcode._04_el;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/studentList2")
public class StudentListServlet2 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//准备一个student集合
		Student s1 = new Student();
		s1.setId(1L);
		s1.setName("刘备");
		s1.setAge(20);
		s1.setFavs(new String[]{"抽烟","喝酒","烫头"});
		List<Student> students = new ArrayList<>();
		
		Student s2 = new Student();
		s2.setId(2L);
		s2.setName("张飞");
		s2.setAge(20);
		s2.setFavs(new String[]{"抽烟","烫头"});
	
		students.add(s1);
		students.add(s2);
		//将集合共享给list2.jsp
		req.setAttribute("students", students);
		req.getRequestDispatcher("/foreach.jsp").forward(req, res);
	}
}
