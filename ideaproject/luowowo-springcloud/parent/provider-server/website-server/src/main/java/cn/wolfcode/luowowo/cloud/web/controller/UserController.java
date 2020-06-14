package cn.wolfcode.luowowo.cloud.web.controller;

import cn.wolfcode.luowowo.cloud.common.Result;
import cn.wolfcode.luowowo.cloud.feign.UserFeignApi;
import cn.wolfcode.luowowo.cloud.web.fegin.SmsFeignApi;
import cn.wolfcode.luowowo.cloud.web.msg.WebsiteCodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserFeignApi userFeignApi;
    @Autowired
    private SmsFeignApi smsFeignApi;

    @RequestMapping("/checkPhone")
    public Result checkPhone(String phone){
        Result<Boolean> result = userFeignApi.checkPhone(phone);
        System.out.println(result);
        if (result == null) {
            return Result.error(WebsiteCodeMsg.MSG_SERVER_ERROR);
        }
        return result;
    }

    @PostMapping("/regist")
    public Object regist(String phone,String nickname,String password,String rpassword,String verifyCode){
        //调用消息服务校验,验证码是否正确
        Result result = smsFeignApi.checkVerifyCode(phone,verifyCode);
        if (result == null) {
            return Result.error(WebsiteCodeMsg.MSG_SERVER_ERROR);
        }
        if (result.hasError()){
            return result;
        }
        //注册
        Result result1 = userFeignApi.regist(phone,nickname,password,rpassword);
        if (result1 == null) {
            return Result.error(WebsiteCodeMsg.MEMBER_SERVER_ERROR);
        }
        return result1;
    }
}
