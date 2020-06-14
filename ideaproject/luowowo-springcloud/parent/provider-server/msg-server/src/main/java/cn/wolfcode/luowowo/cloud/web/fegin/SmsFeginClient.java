package cn.wolfcode.luowowo.cloud.web.fegin;

import cn.wolfcode.luowowo.cloud.common.Result;
import cn.wolfcode.luowowo.cloud.service.ISmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsFeginClient implements SmsFeignApi{
    @Autowired
    private ISmsService smsService;
    @Override
    public Result sendVerifyCode(String phone) {
        smsService.sendVerifyCode(phone);
        return Result.success(null);
    }

    @Override
    public Result checkVerifyCode(String phone, String verifyCode) {
        return Result.success(smsService.checkVerifyCode(phone,verifyCode));
    }
}
