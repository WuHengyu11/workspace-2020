package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Department;
import cn.wolfcode.qo.JsonResult;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IDepartmentService;
import cn.wolfcode.util.RequiredPermission;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        PageInfo<Department> pageInfo = departmentService.query(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "department/list";
    }

    @RequiredPermission(value = "部门删除",expression = "department:delete")
    @RequestMapping("/delete")
    public JsonResult delete(Long id){
        try{
            if (id != null) {
                departmentService.delete(id);
            }
            return new JsonResult();
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(false,"操作失败,请联系管理员");
        }
    }

    @RequiredPermission(value = "部门编辑",expression = "department:saveOrUpdate")
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Department department){
        try{
            if (department.getId() != null) {
                departmentService.update(department);
            }else {
                departmentService.save(department);
            }
            return new JsonResult();
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(false,"操作失败,请联系管理员");
        }
    }
}
