package cn.wolfcode.luowowo.cloud.feign.Hystrix;

import cn.wolfcode.luowowo.cloud.common.Result;
import cn.wolfcode.luowowo.cloud.feign.UserFeignApi;
import org.springframework.stereotype.Component;

@Component
public class UserFeignHystrix implements UserFeignApi {
    @Override
    public Result<Boolean> checkPhone(String phone) {
        return null;
    }
}
