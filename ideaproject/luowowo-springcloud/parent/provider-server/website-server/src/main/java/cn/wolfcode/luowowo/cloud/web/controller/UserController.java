package cn.wolfcode.luowowo.cloud.web.controller;

import cn.wolfcode.luowowo.cloud.common.Result;
import cn.wolfcode.luowowo.cloud.feign.UserFeignApi;
import cn.wolfcode.luowowo.cloud.web.msg.WebsiteCodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserFeignApi userFeignApi;

    @RequestMapping("/checkPhone")
    public Result checkPhone(String phone){
        Result<Boolean> result = userFeignApi.checkPhone(phone);
        if (result == null) {
            return Result.error(WebsiteCodeMsg.MEMBER_SERVER_ERROR);
        }
        return result;
    }
}
