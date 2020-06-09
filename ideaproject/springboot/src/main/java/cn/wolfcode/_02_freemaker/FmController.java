package cn.wolfcode._02_freemaker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FmController {
    @RequestMapping("/fm")
    public String fm(Model model){
        model.addAttribute("msg","hello springboot");
        System.out.println(1/0);
        return "freemaker/fm";
    }
}
