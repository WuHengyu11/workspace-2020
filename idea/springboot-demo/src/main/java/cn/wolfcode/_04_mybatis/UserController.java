package cn.wolfcode._04_mybatis;

import cn.wolfcode._04_mybatis.domain.User;
import cn.wolfcode._04_mybatis.service.IUserService;
import cn.wolfcode._04_mybatis.service.impl.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/user")
    @ResponseBody
    public Object save(){
        User user = new User();
        user.setAge(222);
        user.setName("ddd");
        userService.save(user);
        return "ok";
    }



}

