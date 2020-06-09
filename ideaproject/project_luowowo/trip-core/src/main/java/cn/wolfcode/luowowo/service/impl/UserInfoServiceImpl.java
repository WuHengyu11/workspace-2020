package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.UserInfo;
import cn.wolfcode.luowowo.exception.LogicException;
import cn.wolfcode.luowowo.redis.impl.UserInfoRedisServiceImpl;
import cn.wolfcode.luowowo.repository.UserInfoRepository;
import cn.wolfcode.luowowo.service.IUserInfoService;
import cn.wolfcode.luowowo.util.AssertUtil;
import cn.wolfcode.luowowo.util.Consts;

import cn.wolfcode.luowowo.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserInfoRedisServiceImpl userInfoRedisService;
    @Override
    public UserInfo get(String id) {
        Optional<UserInfo> optional = userInfoRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public boolean checkPhone(String phone) {
        UserInfo userInfo = userInfoRepository.findByPhone(phone);
        return userInfo != null;
    }

    @Override
    public void sendVerifyCode(String phone, String smsUrl, String smsAppkey) {
        //创建短信验证码:4位
        String code = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 4);
        //创建短信内容
        StringBuilder stringBuilder = new StringBuilder(80);
        stringBuilder.append("【创信】您注册的短信验证码:").append(code).append(",请在").append(Consts.VERIFY_CODE_VAI_TIME).append("分钟内使用!");
        System.out.println(stringBuilder.toString());
        //短信接口
        String host = "https://zwp.market.alicloudapi.com";
        String path = "/sms/sendv2";
        String method = "GET";
        String appcode = "3b4a85e829864c26b01b1d307380eac1";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("content", "【云通知】您的验证码是"+code+"。如非本人操作，请忽略本短信");
        querys.put("mobile", phone);
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.err.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //发送短信,java发起http请求 https://way.jd.com/kaixintong/kaixintong 2bc4702e615a072f337f30778971f684
        //springboot提供的restTemplate
        /*
        RestTemplate restTemplate = new RestTemplate();
        //参数1:请求路径url
        String url = "https://way.jd.com/BABO/sms?mobile={1}&msg={2}&appkey={3}";
        //参数2:请求结果,返回数据需要转换数据类
        //参数3:请求参数
        String appkey = "2bc4702e615a072f337f30778971f684";
        String content = "【创信】你的验证码是：" +code+ "，3分钟内有效！";
        String result = restTemplate.getForObject(smsUrl, String.class, phone,"【巴卜技术】您的验证码是"+code+",若非本人操作请忽略",appkey);
        System.out.println(result);
         */
        //将code缓存到redis中
        userInfoRedisService.setVerifyCode(phone,code);
    }

    @Override
    public void regist(String phone, String nickname, String password, String rpassword, String verifyCode) {
        //判断参数是否为空
        AssertUtil.hasLength(phone,"手机号码不能为空");
        AssertUtil.hasLength(nickname,"昵称不能为空");
        AssertUtil.hasLength(password,"密码不能为空");
        AssertUtil.hasLength(rpassword,"确认密码不能为空");
        AssertUtil.hasLength(verifyCode,"验证码不能为空");
        //判断两次密码是否一致
        AssertUtil.isEquals(password,rpassword,"两次输入的密码不一致");
        //手机号码格式是否正确
        //定义手机号的规则
        //String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\\\d{8}$";
        //调用功能 判断
//        boolean flag = phone.matches(regex);
//        if (!flag){
//            throw new RuntimeException("手机号码格式不正确");
//        }
        //手机号码是否已经被注册
        if (this.checkPhone(phone)){
            throw new LogicException("该手机号码已经被注册");
        }
        //验证码是否正确
        //传入的验证码:verifyCode ,之前存到redis的验证码
        String code = userInfoRedisService.getVerifyCode(phone);
        if (code == null || !code.equalsIgnoreCase(verifyCode)){
            throw new LogicException("验证码失效或者错误");
        }
        //注册逻辑
        UserInfo userInfo = new UserInfo();
        userInfo.setNickname(nickname);
        userInfo.setPhone(phone);
        userInfo.setEmail("");
        userInfo.setPassword(password);
        userInfo.setLevel(1);
        userInfo.setCity("内蒙古");
        userInfo.setHeadImgUrl("/images/default.jpg");
        userInfo.setInfo("");
        userInfo.setState(UserInfo.STATE_NORMAL);
        userInfoRepository.save(userInfo);
        }

    @Override
    public UserInfo login(String username, String password) {
        AssertUtil.hasLength(username,"用户名不能为空");
        AssertUtil.hasLength(password,"密码不能为空");
        UserInfo user = userInfoRepository.findByPhoneAndPassword(username,password);
        if (user == null) {
            throw new LogicException("账号或密码错误,登录失败!");
        }
        user.setPassword(null);
        return user;
    }
}
