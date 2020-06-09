package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.domain.Region;
import cn.wolfcode.luowowo.query.RegionQuery;
import cn.wolfcode.luowowo.service.IDestinationService;
import cn.wolfcode.luowowo.service.IRegionService;
import cn.wolfcode.luowowo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("region")
public class RegionController {
    @Autowired
    private IRegionService regionService;
    @Autowired
    private IDestinationService destinationService;
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")RegionQuery qo){
        //分页对象
        //page  --spring-data-mongodb分页对象-针对mongodb数据库
        //pageInfo --mybatis-helper插件分页对象-针对mysql数据库
        Page page = regionService.query(qo);
        model.addAttribute("page",page);
        return "region/list";
    }

    @RequestMapping("/getDestByDeep")
    @ResponseBody
    public Object getDestByDeep(int deep){
        return destinationService.listAll();

    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Region region){
        regionService.saveOrUpdate(region);
        return JsonResult.success();
    }

    @RequestMapping("/changeHotValue")
    @ResponseBody
    public Object changeHotValue(int hot, String id){
        regionService.changeHotValue(hot,id);
        return JsonResult.success();
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id){
        regionService.delete(id);
        return JsonResult.success();
    }

    @RequestMapping("/getDestByRegionId")
    @ResponseBody
    public Object getDestByRegionId(String rid){
        return regionService.getDestByRegionId(rid);
    }
}
