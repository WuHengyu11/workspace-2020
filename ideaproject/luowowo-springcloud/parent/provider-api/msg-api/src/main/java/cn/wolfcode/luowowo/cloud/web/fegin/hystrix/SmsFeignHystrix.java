package cn.wolfcode.luowowo.cloud.web.fegin.hystrix;

import cn.wolfcode.luowowo.cloud.common.Result;
import cn.wolfcode.luowowo.cloud.web.fegin.SmsFeignApi;
import org.springframework.stereotype.Component;

@Component
public class SmsFeignHystrix implements SmsFeignApi {
    @Override
    public Result<Boolean> sendVerifyCode(String phone) {
        return null;
    }

    @Override
    public Result checkVerifyCode(String phone, String verifyCode) {
        return null;
    }
}
