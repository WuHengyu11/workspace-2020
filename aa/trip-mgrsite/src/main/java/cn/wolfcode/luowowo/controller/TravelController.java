package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.domain.Travel;
import cn.wolfcode.luowowo.query.TravelQuery;
import cn.wolfcode.luowowo.service.IDestinationService;
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
    @Autowired
    private IDestinationService destinationService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")TravelQuery qo){
        //此处分页的Page对象不是之前mybatis里面的PageInfo对象
        //他是spring-data-mongodb 这个组件封装对象.
        Page<Travel> page = travelService.query(qo);

        model.addAttribute("page", page);
        return "travel/list";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Travel travel){
        travelService.saveOrUpdate(travel);
        return JsonResult.success();
    }



    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id){

        travelService.delete(id);

        return  JsonResult.success();
    }

    @RequestMapping("/getContentById")
    @ResponseBody
    public Object getContentById(String id){
        return  JsonResult.success(travelService.get(id).getContent());
    }

    @RequestMapping("/changeState")
    @ResponseBody
    public Object changeState(String id, int state){
        travelService.changeState(id, state);
        return  JsonResult.success();
    }




}