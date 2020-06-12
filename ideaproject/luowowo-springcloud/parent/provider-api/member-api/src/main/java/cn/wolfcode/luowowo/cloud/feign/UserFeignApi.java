package cn.wolfcode.luowowo.cloud.feign;

import cn.wolfcode.luowowo.cloud.common.Result;
import cn.wolfcode.luowowo.cloud.feign.Hystrix.UserFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "member-server",fallback = UserFeignHystrix.class)
public interface UserFeignApi {
    @RequestMapping("/checkPhone")
    Result<Boolean> checkPhone(@RequestParam("phone") String phone);
}
