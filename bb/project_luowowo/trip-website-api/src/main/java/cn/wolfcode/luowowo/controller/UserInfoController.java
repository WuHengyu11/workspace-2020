package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.annotation.RequireLogin;
import cn.wolfcode.luowowo.domain.UserInfo;
import cn.wolfcode.luowowo.exception.LogicException;
import cn.wolfcode.luowowo.redis.IUserInfoRedisService;
import cn.wolfcode.luowowo.service.IUserInfoService;
import cn.wolfcode.luowowo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserInfoController {
    @Value("${sms.host}")
    private String smsUrl;
    @Value("${sms.appkey}")
    private String smsAppkey;

    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IUserInfoRedisService userInfoRedisService;
    @RequestMapping("/get")
    public void get(){};
    @RequestMapping("/checkPhone")
    public Object checkPhone(String phone){
        boolean ret = userInfoService.checkPhone(phone);
        return !ret;
    }

    @RequestMapping("/sendVerifyCode")
    public Object sendVerifyCode(String phone){
        userInfoService.sendVerifyCode(phone,smsUrl,smsAppkey);
        return JsonResult.success();
    }

    @PostMapping("/regist")
    public Object regist(String phone,String nickname,String password,String rpassword,String verifyCode){
        userInfoService.regist(phone,nickname,password,rpassword,verifyCode);
        return JsonResult.success();
    }

    @PostMapping("/login")
    public Object login(String username,String password){
        UserInfo user = userInfoService.login(username, password);
        //将用户设置到redis中
        String token = userInfoRedisService.setToken(user);
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("user",user);
        //token user
        return JsonResult.success(map);
    }
    @RequireLogin
    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(HttpServletRequest request){
        String token = request.getHeader("token");
        UserInfo userInfo = userInfoRedisService.getUserByToken(token);
        return JsonResult.success(userInfo);
    }
}
