package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.Employee;
import cn.wolfcode.qo.EmployeeQueryObject;
import cn.wolfcode.qo.JsonResult;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IDepartmentService;
import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.service.IRoleService;
import cn.wolfcode.util.RequiredPermission;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    @RequiredPermission(value = "员工页面",expression = "employee:list")
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") EmployeeQueryObject qo){
        Subject subject = SecurityUtils.getSubject();
//        System.out.println("HR_MGR"+subject.hasRole("HR_MGR"));
//        System.out.println("ORDER_MGR"+subject.hasRole("ORDER_MGR"));
        System.out.println("employee:list:"+subject.isPermitted("employee:list"));
        System.out.println("role:delete:"+subject.isPermitted("role:delete"));
        System.out.println("department:delete:"+subject.isPermitted("department:delete"));

        PageInfo<Employee> pageInfo = employeeService.query(qo);
        model.addAttribute("pageInfo",pageInfo);
        List<Department> departments = departmentService.listAll();
        model.addAttribute("departments",departments);
        return "employee/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id){
        employeeService.delete(id);
        return "redirect:/employee/list.do";
    }

    @RequiredPermission(value = "员工批量删除",expression = "employee:batchDelete")
    @RequestMapping("/batchDelete")
    @ResponseBody
    public JsonResult batchDelete(Long[] ids){
        System.out.println(ids);
        try {
            if (ids != null && ids.length > 0){
                employeeService.batchDelete(ids);
            }
            return new JsonResult();
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(false,"操作失败,请联系管理员");
        }
//        employeeService.delete(ids);
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
    public String saveOrUpdate(Employee employee, Long[] ids){
        //@RequestParam("ids[]")
        if (employee.getId() != null) {
            employeeService.update(employee,ids);
        }else {
            employeeService.save(employee,ids);
        }
        return "redirect:/employee/list.do";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
       // session.invalidate();
        return "redirect:/login.html";
    }

    @RequestMapping("/exportXls")
    public void exportXls(HttpServletResponse response) throws IOException {
       //文件下载响应头(让浏览器访问资源的时候已下载的方式打开)
        response.setHeader("Content-Disposition","attachment;filename=employee.xls");
        //创建excel文件
        Workbook wb = employeeService.exportXls();
        //把文件输出到浏览器
        wb.write(response.getOutputStream());
    }

    @RequestMapping("/importXls")
    @ResponseBody
    public JsonResult importXls(MultipartFile file) throws IOException {
        employeeService.importXls(file);
        return new JsonResult();
    }
}
