package cn.wolfcode.web.controller;


import cn.wolfcode.domain.Customer;
import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.SystemDictionaryItem;
import cn.wolfcode.qo.CustomerQuery;
import cn.wolfcode.qo.JsonResult;
import cn.wolfcode.service.ICustomerService;
import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.service.ISystemDictionaryItemService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ISystemDictionaryItemService systemDictionaryItemService;

    //@RequiresPermissions(value = {"customer:list","客户页面"},logical = Logical.OR)
    @RequestMapping("/potentialList")
    public String list(Model model, @ModelAttribute("qo") CustomerQuery qo){
       //只查询潜在客户状态的数据
        qo.setStatus(Customer.STATUS_COMMON);
        PageInfo<Customer> pageInfo = customerService.query(qo);
        model.addAttribute("pageInfo", pageInfo);
        //管理员或者经理进来潜在客户页面,可以看到所有数据,如果是普通客户只能看到自己的
        Subject subject = SecurityUtils.getSubject();
        if (!(subject.hasRole("Admin") || subject.hasRole("Market_Manager"))){
            Employee employee = (Employee) subject.getPrincipal();
            qo.setSellerId(employee.getId());
        }

        //查询销售人员下拉框的数据
        List<Employee> sellers = employeeService.selectByRoleSn("Market_Manage", "Market");
        model.addAttribute("sellers",sellers);

        List<SystemDictionaryItem> jobs = systemDictionaryItemService.selectByDicsn("job");
        model.addAttribute("jobs", jobs);

        List<SystemDictionaryItem> sources = systemDictionaryItemService.selectByDicsn("source");
        model.addAttribute("sources", sources);

        //交流方式下拉框数据communicationMethod
        List<SystemDictionaryItem> communicationMethod = systemDictionaryItemService.selectByDicsn("communicationMethod");
        model.addAttribute("ccts", communicationMethod);
        return "customer/potentialList";
    }

    @RequestMapping("/delete")
    @RequiresPermissions(value = {"customer:delete","客户删除"},logical = Logical.OR)
    @ResponseBody
    public JsonResult delete(Long id){
        if (id != null) {
            customerService.delete(id);
        }
        return new JsonResult();
    }


    @RequestMapping("/saveOrUpdate")
    @RequiresPermissions(value = {"customer:saveOrUpdate","客户新增/编辑"},logical = Logical.OR)
    @ResponseBody
    public JsonResult saveOrUpdate(Customer customer){
        if (customer.getId() != null) {
            customerService.update(customer);
        }else {
            customerService.save(customer);
        }
        return new JsonResult();
    }
}
