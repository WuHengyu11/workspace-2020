package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Salary;
import cn.wolfcode.domain.SystemDictionaryItem;
import cn.wolfcode.qo.JsonResult;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.qo.SalaryQueryObject;
import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.service.ISalaryService;
import cn.wolfcode.service.ISystemDictionaryItemService;
import cn.wolfcode.util.RequiredPermission;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/salary")
public class SalaryController {
    //关联service
    @Autowired
    private ISalaryService salaryService;

    @Autowired
    private ISystemDictionaryItemService systemDictionaryItemService;

    @Autowired
    private IEmployeeService employeeService;

    //@RequiredPermission(value = "工资页面",expression = "salary:list")
    @RequiresPermissions(value = {"salary:list","工资页面"},logical = Logical.OR)
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") SalaryQueryObject qo){
        PageInfo<Salary> pageInfo = salaryService.query(qo);
        model.addAttribute("pageInfo",pageInfo);
        List<SystemDictionaryItem> payout = systemDictionaryItemService.selectByDicsn("payout");
        model.addAttribute("payout",payout);
        List<Employee> employees = employeeService.listAll();
        model.addAttribute("employees",employees);
        return "salary/list";
    }

    @RequiredPermission(value = "工资删除",expression = "salary:delete")
    @RequestMapping("/delete")
    public JsonResult delete(Long id){
        try{
            if (id != null) {
                salaryService.delete(id);
            }
            return new JsonResult();
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(false,"操作失败,请联系管理员");
        }
    }

    @RequiredPermission(value = "工资编辑",expression = "salary:saveOrUpdate")
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Salary salary){
        try{
            if (salary.getId() != null) {
                salaryService.update(salary);
            }else {
                salaryService.save(salary);
            }
            return new JsonResult();
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(false,"操作失败,请联系管理员");
        }
    }
}
