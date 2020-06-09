package com.wolfcode.mongodb.controller;

import com.wolfcode.mongodb.domain.User;
import com.wolfcode.mongodb.service.IUserService;
import com.wolfcode.mongodb.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    public Object list(){
        List<User> list = userService.list();
        return new JsonResult(list);
    }

    @PostMapping("saveOrUpdate")
    public Object saveOrUpdate(User user){
        if (StringUtils.hasLength(user.get_id())) {
            userService.update(user);
        }else {
            userService.save(user);
        }
        return new JsonResult();
    }
}
