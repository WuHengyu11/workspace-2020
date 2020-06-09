package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.domain.Travel;
import cn.wolfcode.luowowo.query.TravelQuery;
import cn.wolfcode.luowowo.service.ITravelService;
import cn.wolfcode.luowowo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("travel")
public class TravelController {
    @Autowired
    private ITravelService travelService;


    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")TravelQuery qo){
        //分页对象
        //page  --spring-data-mongodb分页对象-针对mongodb数据库
        //pageInfo --mybatis-helper插件分页对象-针对mysql数据库
        Page page = travelService.query(qo);
        model.addAttribute("page",page);
        return "travel/list";
    }
    
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id){
        travelService.delete(id);
        return JsonResult.success();
    }

    @RequestMapping("/changeState")
    @ResponseBody
    public Object changeState(Travel travel){
        travelService.changeState(travel);
        return JsonResult.success();
    }

    @RequestMapping("/getContentById")
    @ResponseBody
    public Object getContentById(String id){
        return JsonResult.success(travelService.getContentById(id));
    }
}
