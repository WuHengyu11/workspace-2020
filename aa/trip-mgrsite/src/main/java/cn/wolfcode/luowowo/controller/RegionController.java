package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.Region;
import cn.wolfcode.luowowo.query.RegionQuery;
import cn.wolfcode.luowowo.service.IDestinationService;
import cn.wolfcode.luowowo.service.IRegionService;
import cn.wolfcode.luowowo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("region")
public class RegionController {
    @Autowired
    private IRegionService regionService;
    @Autowired
    private IDestinationService destinationService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")RegionQuery qo){
        //此处分页的Page对象不是之前mybatis里面的PageInfo对象
        //他是spring-data-mongodb 这个组件封装对象.
        Page<Region> page = regionService.query(qo);

        model.addAttribute("page", page);
        return "region/list";
    }

    @RequestMapping("/getDestByDeep")
    @ResponseBody
    public Object getDestByDeep(int deep){
        List<Destination> list = destinationService.list();
        return list;
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Region region){
        regionService.saveOrUpdate(region);
        return JsonResult.success();
    }

    @RequestMapping("/getDestByRegionId")
    @ResponseBody
    public Object getDestByRegionId(String rid){

        return  destinationService.getDestByRegionId(rid);
    }
    @RequestMapping("/changeHotValue")
    @ResponseBody
    public Object changeHotValue(String id, int hot){

        regionService.changeHotValue(id, hot);

        return  JsonResult.success();
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id){

        regionService.delete(id);

        return  JsonResult.success();
    }

}