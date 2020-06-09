package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Department;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IDepartmentService;
import cn.wolfcode.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    //关联service
    @Autowired
    private IDepartmentService departmentService;

    @RequiredPermission(value = "部门页面",expression = "department:list")
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")QueryObject qo){
        PageResult<Department> pageResult = departmentService.query(qo);
        model.addAttribute("pageResult",pageResult);
        return "department/list";
    }

    @RequiredPermission(value = "部门删除",expression = "department:delete")
    @RequestMapping("/delete")
    public String delete(Long id){
        departmentService.delete(id);
        return "redirect:/department/list.do";
    }

    @RequiredPermission(value = "部门添加",expression = "department:input")
    @RequestMapping("/input")
    public String input(Model model,Long id){
        if (id != null) {
            model.addAttribute("departments",departmentService.get(id));
        }
        return "department/input";
    }

    @RequiredPermission(value = "部门编辑",expression = "department:saveOrUpdate")
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Department department){
        if (department.getId() != null) {
            departmentService.update(department);
        }else {
            departmentService.save(department);
        }
        return "redirect:/department/list.do";
    }
}
