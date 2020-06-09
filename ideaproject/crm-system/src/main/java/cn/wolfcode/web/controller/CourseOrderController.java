package cn.wolfcode.web.controller;

import cn.wolfcode.domain.CourseOrder;
import cn.wolfcode.domain.Customer;
import cn.wolfcode.domain.SystemDictionaryItem;
import cn.wolfcode.qo.CourseOrderQuery;
import cn.wolfcode.qo.JsonResult;
import cn.wolfcode.service.ICourseOrderService;
import cn.wolfcode.service.ICustomerService;
import cn.wolfcode.service.ISystemDictionaryItemService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/courseorder")
public class CourseOrderController {
    @Autowired
    private ICourseOrderService courseOrderService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ISystemDictionaryItemService systemDictionaryItemService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") CourseOrderQuery qo){
        System.out.println(qo);
        PageInfo<CourseOrder> pageInfo = courseOrderService.query(qo);
        model.addAttribute("pageInfo",pageInfo);
        List<Customer> customers = customerService.listAll();
        model.addAttribute("customers",customers);
        List<SystemDictionaryItem> items = systemDictionaryItemService.selectByParentId();
        model.addAttribute("items",items);
        return "courseorder/list";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(CourseOrder co){
        Date time = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current = sdf.format(time);
        System.out.println(current);
        customerService.updateById(co.getCustomer().getId());
        co.setInputTime(current);
        try {
            if (co.getId() != null) {
                courseOrderService.update(co);
            }else {
                courseOrderService.save(co);
            }
            return new JsonResult(true,"操作成功");
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(false,"操作失败,请联系管理员");
        }
    }
}
