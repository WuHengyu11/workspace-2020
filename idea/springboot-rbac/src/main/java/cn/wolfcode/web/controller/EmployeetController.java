package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Role;
import cn.wolfcode.qo.EmployeeQueryObject;
import cn.wolfcode.service.IDepartmentService;
import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.service.IRoleService;
import cn.wolfcode.util.JsonResult;
import cn.wolfcode.util.RequiredPermission;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeetController {
    @Autowired
    private IEmployeeService employeeServiceImpl;
    @Autowired
    private IDepartmentService departmentServiceImpl;

    @Autowired
    private IRoleService roleServiceImpl;

    @RequiredPermission(value = "员工列表",expression = "employee:list")
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")EmployeeQueryObject qo){
        PageInfo pageInfo = employeeServiceImpl.query(qo);
        model.addAttribute("result", pageInfo);

        List<Department> departments = departmentServiceImpl.selectAll();
        model.addAttribute("departments",departments);
        return  "employee/list";
    }


    @RequiredPermission(value = "员工删除",expression = "employee:delete")
    @RequestMapping("/delete")
    public String delete(Long id){
        if(id !=null){
            employeeServiceImpl.delete(id);
        }
        return "redirect:/employee/list.do";
    }


    @RequiredPermission(value = "员工编辑",expression = "employee:input")
    @RequestMapping("/input")
    public String input(Long id,Model model){
        //查询添加角色
        List<Role> roles = roleServiceImpl.listAll();
        model.addAttribute("roles",roles);

        List<Department> departments = departmentServiceImpl.selectAll();
        model.addAttribute("departments",departments);
        if(id != null){
            Employee employee = employeeServiceImpl.selectOne(id);
            model.addAttribute("employee",employee);
        }
        return "/employee/input";
    }

    @RequiredPermission(value = "员工存入和更新",expression = "employee:saveOrUpdate")
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Employee employee,Long[] ids){

        if(employee.getId() != null){
            employeeServiceImpl.update(employee,ids);
        }else{
            employeeServiceImpl.save(employee,ids);

        }
        return "redirect:/employee/list.do";
    }

    @RequestMapping("/inputPwd")  // .do
    public String  inputPwd(Model model,Long id){
        model.addAttribute("id",id);
        return "/employee/updatePwd"; //jsp
    }


    @RequestMapping("/updatePwd")
    @ResponseBody
    public JsonResult updatePwd(Long id, String oldPassword,String newPassword,HttpSession session){
        Employee employee = (Employee) session.getAttribute("EMPLOYEE_IN_SESSION");
        if(employee.getPassword().equals(oldPassword)){
            employeeServiceImpl.updatePwd(id,newPassword);
            return new JsonResult();
        }else{
            return new JsonResult(false,"旧密码错误,修改失败");
        }
    }

    @RequestMapping("/setPwd")
    public String  setPwd(Model model,Long id){
        model.addAttribute("id",id);
        return "/employee/resetPwd";
    }
    @RequestMapping("/resetPwd")
        public JsonResult resetPwd(Long id,String newPassword,HttpSession session){
            Employee employee = (Employee) session.getAttribute("EMPLOYEE_IN_SESSION");
            if(newPassword!=null){
                employeeServiceImpl.resetPwd(id,newPassword);
                return new JsonResult();
            }else{
                return new JsonResult(false,"密码修改失败");
            }
        }
    }




