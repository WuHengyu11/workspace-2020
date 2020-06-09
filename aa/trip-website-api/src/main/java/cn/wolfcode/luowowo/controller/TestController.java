package cn.wolfcode.luowowo.controller;


import cn.wolfcode.luowowo.util.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/test")
    public Object test(){
        return JsonResult.success("访问进来....");
    }

    @GetMapping("/test1")
    public Object test1(String aa, String bb, String cc, String sign){
        return JsonResult.success("页面传参：" + aa +"," + bb +"," +cc + "," + sign);
    }
}
