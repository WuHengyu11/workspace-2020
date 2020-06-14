package cn.wolfcode.luowowo.cloud.web.fegin;

import cn.wolfcode.luowowo.cloud.common.Result;
import cn.wolfcode.luowowo.cloud.web.fegin.hystrix.SmsFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msg-server",fallback = SmsFeignHystrix.class)
public interface SmsFeignApi {
    @RequestMapping("/sendVerifyCode")
    Result sendVerifyCode(@RequestParam("phone") String phone);

    @RequestMapping("/checkVerifyCode")
    Result checkVerifyCode(@RequestParam("phone") String phone,@RequestParam("verifyCode") String verifyCode);
}
