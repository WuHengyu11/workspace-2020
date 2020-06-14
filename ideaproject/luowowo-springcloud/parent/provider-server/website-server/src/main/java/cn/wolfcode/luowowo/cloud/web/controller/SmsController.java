package cn.wolfcode.luowowo.cloud.web.controller;

import cn.wolfcode.luowowo.cloud.common.Result;
import cn.wolfcode.luowowo.cloud.web.fegin.SmsFeignApi;
import cn.wolfcode.luowowo.cloud.web.msg.WebsiteCodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {
    @Autowired
    private SmsFeignApi smsFeignApi;

    @RequestMapping("/sendVerifyCode")
    public Result<Boolean> sendVerifyCode(String phone){
        Result result = smsFeignApi.sendVerifyCode(phone);
        if (result == null) {
            return Result.error(WebsiteCodeMsg.DEFAULT_ERROR);
        }
        return result;
        //return Result.success()
    }
}
