package cn.wolfcode.luowowo.cloud.web.feign;

import cn.wolfcode.luowowo.cloud.common.Result;
import cn.wolfcode.luowowo.cloud.feign.UserFeignApi;

public class UserFeignClient implements UserFeignApi {
    @Override
    public Result<Boolean> checkPhone(String phone) {
        return null;
    }
}
