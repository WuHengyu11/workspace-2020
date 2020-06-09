package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Department;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    //查询
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")QueryObject qo){
        PageResult<Department> pageResult = departmentService.query(qo);
        model.addAttribute("pageResult",pageResult);
        return "department/list";
    }

    //去新增或修改的页面（根据id判断是否新增和修改）
    @RequestMapping("/input")
    public String input(Model model,Long id){
        if (id != null) {
            model.addAttribute("dept",departmentService.get(id));
        }
        return "department/input";
    }

    //保存修改
    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Department dept){
        if (dept.getId() != null) {
            departmentService.update(dept);
        }else {
            departmentService.save(dept);
        }
        return "redirect:/department/list.do";
    }

    //删除
    @RequestMapping("/delete")
    public String delete(Long id){
        if (id != null) {
            departmentService.delete(id);
        }
        return "redirect:/department/list.do";
    }
}
