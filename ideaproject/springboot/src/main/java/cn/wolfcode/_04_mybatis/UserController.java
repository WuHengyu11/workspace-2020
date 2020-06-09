package cn.wolfcode._04_mybatis;

import cn.wolfcode._04_mybatis.domain.User;
import cn.wolfcode._04_mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;
    @RequestMapping("/user/save")
    @ResponseBody
    public Object save(){
        User user = new User();
        user.setPassword("zf");
        user.setUsername("zf");
        userService.save(user);
        return "ok";
    }
}
