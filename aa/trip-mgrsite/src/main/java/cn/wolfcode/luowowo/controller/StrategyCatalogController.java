package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.domain.StrategyCatalog;
import cn.wolfcode.luowowo.query.StrategyCatalogQuery;
import cn.wolfcode.luowowo.service.IDestinationService;
import cn.wolfcode.luowowo.service.IStrategyCatalogService;
import cn.wolfcode.luowowo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("strategyCatalog")
public class StrategyCatalogController {
    @Autowired
    private IStrategyCatalogService strategyCatalogService;
    @Autowired
    private IDestinationService destinationService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")StrategyCatalogQuery qo){
        Page<StrategyCatalog> page = strategyCatalogService.query(qo);

        model.addAttribute("page", page);
        return "strategyCatalog/list";
    }



    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(StrategyCatalog strategyCatalog){
        strategyCatalogService.saveOrUpdate(strategyCatalog);
        return JsonResult.success();
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id){

        strategyCatalogService.delete(id);

        return  JsonResult.success();
    }

}