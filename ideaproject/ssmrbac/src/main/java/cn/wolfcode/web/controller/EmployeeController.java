package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.Employee;
import cn.wolfcode.qo.EmployeeQueryObject;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.service.IDepartmentService;
import cn.wolfcode.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;

    //查询
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") EmployeeQueryObject qo){
        PageResult<Employee> pageResult = employeeService.query(qo);
        model.addAttribute("pageResult",pageResult);
        List<Department> departments = departmentService.listAll();
        model.addAttribute("departments",departments);
        return "employee/list";
    }

    //去新增或修改的页面（根据id判断是否新增和修改）
    @RequestMapping("/input")
    public String input(Model model,Long id){
        model.addAttribute("departments",departmentService.listAll());
        if (id != null) {
            model.addAttribute("employee",employeeService.get(id));
        }
        return "employee/input";
    }

    //保存修改
    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Employee employee){
        if (employee.getId() != null) {
            employeeService.update(employee);
        }else {
            employeeService.save(employee);
        }
        return "redirect:/employee/list.do";
    }

    //删除
    @RequestMapping("/delete")
    public String delete(Long id){
        if (id != null) {
            employeeService.delete(id);
        }
        return "redirect:/employee/list.do";
    }
}
