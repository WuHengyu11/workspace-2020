package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.qo.JsonResult;
import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/updatePwd")
    @ResponseBody
    public JsonResult updatePwd(String oldPassword, String newPassword, HttpSession session){
        Employee currentUser = UserContext.getCurrentUser();
            if (oldPassword.equals(currentUser.getPassword())) {
                employeeService.updatePwd(newPassword, currentUser.getId());
                session.invalidate();
                return new JsonResult();
            }else {
            return new JsonResult(false,"修改失败");
        }
    }
    @RequestMapping("/input")
    public String input(Long id, Model model){
        Employee employee = employeeService.get(id);
        if (id != null) {
            model.addAttribute("employee",employee);
        }
        return "/resetPwd";
    }

    @RequestMapping("/resetPwd")
    public String resetPwd(Long id,String newPassword){
        System.out.println(newPassword);
        employeeService.updatePwd(newPassword,id);
        return "redirect:/login.html";
    }
}
