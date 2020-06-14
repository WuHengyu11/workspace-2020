package cn.wolfcode.luowowo.cloud.web.feign;

import cn.wolfcode.luowowo.cloud.common.Result;
import cn.wolfcode.luowowo.cloud.feign.UserFeignApi;
import cn.wolfcode.luowowo.cloud.service.IUserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserFeignClient implements UserFeignApi {
    @Autowired
    private IUserSevice userSevice;
    @Override
    public Result<Boolean> checkPhone(String phone) {
        return Result.success(userSevice.checkPhone(phone));
    }

    @Override
    public Result regist(String phone, String nickname, String password, String rpassword) {
        userSevice.regist(phone,nickname,password,rpassword);
        return Result.success(null);
    }
}
