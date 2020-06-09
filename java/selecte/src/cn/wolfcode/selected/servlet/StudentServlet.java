package cn.wolfcode.selected.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wolfcode.selected.dao.IStudentDAO;
import cn.wolfcode.selected.domain.Student;
import cn.wolfcode.selected.impl.StudentDAOImpl;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//建立关联对象
		IStudentDAO dao = new StudentDAOImpl();
		
		//调用查询方法
		List<Student> studentList = dao.studentList();
		
		//将集合共享给list2.jsp
		req.setAttribute("studentList", studentList);
		req.getRequestDispatcher("/Student.jsp").forward(req, res);
	}

}
