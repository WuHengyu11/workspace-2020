package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.Employee;
import cn.wolfcode.qo.EmployeeQueryObject;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IDepartmentService;
import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.service.IRoleService;
import cn.wolfcode.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    //关联service
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IRoleService roleService;

    @RequiredPermission(value = "员工页面",expression = "employee:delete")
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") EmployeeQueryObject qo){
        PageResult<Employee> pageResult = employeeService.query(qo);
        model.addAttribute("pageResult",pageResult);
        List<Department> departments = departmentService.listAll();
        model.addAttribute("departments",departments);
        return "employee/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id){
        employeeService.delete(id);
        return "redirect:/employee/list.do";
    }

    @RequestMapping("/input")
    public String input(Model model,Long id){
        if (id != null) {
            model.addAttribute("employees",employeeService.get(id));
        }
        model.addAttribute("roles",roleService.listAll());
        List<Department> departments = departmentService.listAll();
        model.addAttribute("departments",departments);
        return "employee/input";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Employee employee,Long[] ids){
        if (employee.getId() != null) {
            employeeService.update(employee,ids);
        }else {
            employeeService.save(employee,ids);
        }
        return "redirect:/employee/list.do";
    }
}
