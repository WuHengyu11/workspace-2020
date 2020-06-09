package cn.wolfcode._04_el;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/studentList")
public class StudentListServlet extends HttpServlet {

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
		students.add(s1);
		//将集合共享给list2.jsp
		req.setAttribute("students", students);
		req.getRequestDispatcher("/list2.jsp").forward(req, res);
	}
}
