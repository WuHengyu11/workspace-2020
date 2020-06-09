package cn.wolfcode.web.controller;

import cn.wolfcode.domain.User;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.UserQueryObject;
import cn.wolfcode.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    //关联service
    @Autowired
    private IUserService userService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")UserQueryObject qo){
        PageResult<User> pageResult = userService.query(qo);
        model.addAttribute("pageResult",pageResult);
        return "user/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id){
        userService.delete(id);
        return "redirect:/user/list.do";
    }

    @RequestMapping("/input")
    public String input(Long id, Model model){
        if (id != null) {
            model.addAttribute("user",userService.get(id));
        }
        return "user/input";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(User user){
        if (user.getId() != null) {
            userService.update(user);
        }else {
            userService.save(user);
        }
        return "redirect:/user/list.do";
    }
}
