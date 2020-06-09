package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.Banner;
import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.domain.Travel;
import cn.wolfcode.luowowo.query.BannerQuery;
import cn.wolfcode.luowowo.service.IDestinationService;
import cn.wolfcode.luowowo.service.IBannerService;
import cn.wolfcode.luowowo.service.IStrategyService;
import cn.wolfcode.luowowo.service.ITravelService;
import cn.wolfcode.luowowo.util.JsonResult;
import cn.wolfcode.luowowo.util.ParamMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private IBannerService bannerService;


    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private ITravelService travelService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")BannerQuery qo){

        Page<Banner> page = bannerService.query(qo);

        model.addAttribute("page", page);
        return "banner/list";
    }


    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Banner banner){
        bannerService.saveOrUpdate(banner);
        return JsonResult.success();
    }

    @RequestMapping("/changeHotValue")
    @ResponseBody
    public Object changeHotValue(String id, int hot){

        bannerService.changeHotValue(id, hot);

        return  JsonResult.success();
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id){

        bannerService.delete(id);

        return  JsonResult.success();
    }

    @RequestMapping("/getAllType")
    @ResponseBody
    public Object getAllType(){

        //攻略查询
        List<Strategy> sts = strategyService.list();

        //游记
        List<Travel> ts = travelService.list();


        return  JsonResult.success(new ParamMap().put("sts",sts).put("ts",ts));
    }
}