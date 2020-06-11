package cn.wolfcode.luowowo.cloud.web.controller;

import cn.wolfcode.luowowo.cloud.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/checkPhone")
    public Result checkPhone(String phone){
        return Result.success(null);
    }
}
