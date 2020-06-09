package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.query.DestinationQuery;
import cn.wolfcode.luowowo.service.IDestinationService;
import cn.wolfcode.luowowo.service.IDestinationService;
import cn.wolfcode.luowowo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("destination")
public class DestinationController {
    @Autowired
    private IDestinationService destinationService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")DestinationQuery qo){
        //分页对象
        //page  --spring-data-mongodb分页对象-针对mongodb数据库
        //pageInfo --mybatis-helper插件分页对象-针对mysql数据库
        Page page = destinationService.query(qo);
        model.addAttribute("page",page);
        model.addAttribute("toasts",destinationService.getToasts(qo.getParentId()));
        return "destination/list";
    }

}
