package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.qo.JsonResult;
import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.service.IPermissionService;
import cn.wolfcode.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPermissionService permissionService;
    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(String username, String password,HttpSession session) {
        try {
            Employee employee = employeeService.login(username, password);
            //讲登录的用户存到session中
            UserContext.setCurrentUser(employee);
            //把当前登录用户的权限数据存放到session中
            if (!employee.isAdmin()){//判断是否为超管,不是继续查询
                List<String> expression = permissionService.selectExpressionByEmpId(employee.getId());
                UserContext.setPermissions(expression);
            }

            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.html";
    }
}
