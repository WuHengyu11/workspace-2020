package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.annotation.RequireLogin;
import cn.wolfcode.luowowo.annotation.UserParam;
import cn.wolfcode.luowowo.domain.Person;
import cn.wolfcode.luowowo.domain.UserInfo;
import cn.wolfcode.luowowo.redis.service.IStrategyStatisVORedisService;
import cn.wolfcode.luowowo.redis.service.IUserInfoRedisService;
import cn.wolfcode.luowowo.service.IUserInfoService;
import cn.wolfcode.luowowo.util.JsonResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@Api(value = "用户资源",description = "用户资源控制器")
@RequestMapping("users")
public class UserController {

    @Value("${sms.url}")
    private String smsUrl;
    @Value("${sms.appkey}")
    private String appkey;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IUserInfoRedisService userInfoRedisService;


    @Autowired
    private IStrategyStatisVORedisService strategyStatisVORedisService;



    @GetMapping("/get")  //没贴标签, 不需要登录控制
    public Object get(String id){
        return userInfoService.get(id);
    }
    //必须登录之后才可以访问
    @RequireLogin
    @GetMapping("/currentUser")
    public Object currentUser(HttpServletRequest request){

        //已经登录成功过了, 现在想在controller获取当前登录用户信息
        //之前登录信息已经缓存在redis里面了, 此时只需要在此位置获取即可
        //前提: 必须得拿到 之前登录时候, 缓存的token key 值
        //要求：用户访问该接口时, 需要把token发过来.
        // cookie, 原则上, 浏览器发请求时, 可以自动带上cookie信息
        //但是, 这个项目是前后端分离的项目, 客户端不一定是浏览器.
        //所以建议使用请求方式携带数据上controller

        String token = request.getHeader("token");
        UserInfo user = userInfoRedisService.getUserByToken(token);
        return JsonResult.success(user);
    }

    //检查用户手机是否存在
    @GetMapping("/checkPhone")
    public Object checkPhone(String phone){
        //true: 存在
        boolean ret = userInfoService.checkPhone(phone);
        return !ret;
    }

    //进行用户注册
    @ApiOperation(value = "注册功能",notes = "其实就是新增用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "手机号码",name = "phone",dataType = "String",required = true),
            @ApiImplicitParam(value = "昵称",name = "nickname",dataType = "String",required = true),
            @ApiImplicitParam(value = "密码",name = "password",dataType = "String",required = true),
            @ApiImplicitParam(value = "确认密码",name = "rpassword",dataType = "String",required = true),
            @ApiImplicitParam(value = "短信验证码",name = "verifyCode",dataType = "String",required = true)
    })
    @PostMapping("/regist")
    public Object userRegist(String phone, String nickname, String password, String rpassword, String verifyCode){
        userInfoService.userRegist(phone, nickname, password, rpassword, verifyCode);
        return JsonResult.success();
    }


    //短信发送
    @GetMapping("/sendVerifyCode")
    public Object sendVerifode(String phone){
        userInfoService.sendVerifyCode(phone, smsUrl, appkey);
        return JsonResult.success();
    }


    //攻略收藏id集合
    @GetMapping("/strategies/favor")
    public Object statisVo(String sid, String userId){
        List<String> list = Collections.emptyList();
        if(StringUtils.hasLength(userId)){
            list =strategyStatisVORedisService.getSids(userId);
        }
        return JsonResult.success(list);
    }







    @PostMapping("/login")
    public Object userLogin(String username, String password){

        //步骤1: 用户登录操作
        UserInfo userInfo = userInfoService.userLogin(username, password);

        //步骤2: 创建token
        String token = UUID.randomUUID().toString().replaceAll("-", "");

        //步骤3: 使用token作为key, 用户对象做为vlaue存到redis中
        userInfoRedisService.setToken(token, userInfo);

        //步骤4: 将token值传到客户端保存起来
        /**
         *
         * {
         *     code:xx
         *     msg:xx
         *     data:{
         *         token:xxx
         *         user:xxx
         *     }
         * }
         *
         */
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("user", userInfo);
        return JsonResult.success(map);
    }


    //短信发送
    @ApiIgnore
    @ApiResponses({
            @ApiResponse(code=200,message="查询用户信息成功")
    })
    @GetMapping("/person")
    public Object person(Person p){
        return JsonResult.success();
    }

}
