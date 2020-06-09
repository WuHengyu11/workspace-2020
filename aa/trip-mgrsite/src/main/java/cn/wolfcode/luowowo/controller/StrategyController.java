package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.query.StrategyQuery;
import cn.wolfcode.luowowo.service.IDestinationService;
import cn.wolfcode.luowowo.service.IStrategyCatalogService;
import cn.wolfcode.luowowo.service.IStrategyService;
import cn.wolfcode.luowowo.service.IStrategyThemeService;
import cn.wolfcode.luowowo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("strategy")
public class StrategyController {
    @Autowired
    private IStrategyService strategyService;
    @Autowired
    private IDestinationService destinationService;

    @Autowired
    private IStrategyThemeService strategyThemeService;

    @Autowired
    private IStrategyCatalogService strategyCatalogService;





    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")StrategyQuery qo){
        Page<Strategy> page = strategyService.query(qo);

        model.addAttribute("page", page);
        return "strategy/list";
    }

    @RequestMapping("/input")
    public String input(Model model,String id){
        if(StringUtils.hasLength(id)){
            //编辑
            //strategy
            model.addAttribute("strategy",strategyService.get(id));

        }
        //catalogs
        model.addAttribute("catalogs", strategyCatalogService.groupList());

        //themes
        model.addAttribute("themes", strategyThemeService.list());
        return "strategy/input";
    }




    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Strategy strategy){
        strategyService.saveOrUpdate(strategy);
        return JsonResult.success();
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id){

        strategyService.delete(id);

        return  JsonResult.success();
    }

}