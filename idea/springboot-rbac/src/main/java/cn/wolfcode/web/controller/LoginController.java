package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.service.IPermissionService;
import cn.wolfcode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private IEmployeeService employeeServiceImpl;

    @Autowired
    private IPermissionService permissionServiceImpl;

    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(String username, String password, HttpSession session){
        try {
            Employee employee =  employeeServiceImpl.login(username,password);
            System.out.println(employee);
            //把当前用户存放session中
            session.setAttribute("EMPLOYEE_IN_SESSION",employee);
            //把登录的用户权限存到session
            if(!employee.isAdmin()){ //判断是否超级管理员,不是才查询
                List<String> permission = permissionServiceImpl.selectExpressionByEmpid(employee.getId());
                System.out.println(permission);
                session.setAttribute("PERMISSION_IN_SESSION",permission);
            }
            return new JsonResult(); //登录成功
       }catch (Exception e){
           e.printStackTrace();
           return  new JsonResult(false,e.getMessage()); //登陆失败
       }
    }

    @RequestMapping("/logout")
    public String loginout( HttpSession session){
        session.invalidate(); //销毁会话
        return "redirect:/login.html";
    }

}
