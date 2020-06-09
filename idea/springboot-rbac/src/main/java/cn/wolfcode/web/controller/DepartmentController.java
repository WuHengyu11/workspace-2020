package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Department;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IDepartmentService;
import cn.wolfcode.util.RequiredPermission;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @RequiredPermission(value = "部门列表",expression = "department:list")
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")QueryObject qo){
        PageInfo pageInfo = departmentService.query(qo);
        model.addAttribute("result", pageInfo);
        return  "department/list";
    }

    @RequiredPermission(value = "部门删除",expression = "department:delete")
    @RequestMapping("/delete")
    public String delete(Long id){
        if(id !=null){
            departmentService.delete(id);
        }
        return "redirect:/department/list.do";
    }

    @RequiredPermission(value = "部门输入",expression = "department:input")
    @RequestMapping("/input")
    public String input(Long id,Model model){
        if(id != null){
            Department department = departmentService.selectOne(id);
            model.addAttribute("department",department);
        }
        return "department/input";
    }
    @RequiredPermission(value = "部门存储和更新",expression = "department:saveOrUpdate")
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Department department){
        if(department.getId() != null){
            departmentService.update(department);
        }else{
            departmentService.save(department);

        }
        return "redirect:/department/list.do";
    }

}
