package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.query.DestinationQuery;
import cn.wolfcode.luowowo.service.IDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("destination")
public class DestinationController {

    @Autowired
    private IDestinationService destinationService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")DestinationQuery qo){


        //page
        Page<Destination> page = destinationService.query(qo);

        model.addAttribute("page", page);

        //toasts
        model.addAttribute("toasts", destinationService.getToasts(qo.getParentId()));


        return "destination/list";
    }


}