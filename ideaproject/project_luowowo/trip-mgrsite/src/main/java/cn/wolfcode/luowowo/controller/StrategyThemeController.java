package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.domain.StrategyTheme;
import cn.wolfcode.luowowo.query.StrategyThemeQuery;
import cn.wolfcode.luowowo.service.IStrategyThemeService;
import cn.wolfcode.luowowo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("strategyTheme")
public class StrategyThemeController {
    @Autowired
    private IStrategyThemeService strategyThemeService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")StrategyThemeQuery qo){
        //分页对象
        //page  --spring-data-mongodb分页对象-针对mongodb数据库
        //pageInfo --mybatis-helper插件分页对象-针对mysql数据库
        Page page = strategyThemeService.query(qo);
        model.addAttribute("page",page);
        return "strategyTheme/list";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(StrategyTheme strategyTheme){
        strategyThemeService.saveOrUpdate(strategyTheme);
        return JsonResult.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id){
        strategyThemeService.delete(id);
        return JsonResult.success();
    }
}
