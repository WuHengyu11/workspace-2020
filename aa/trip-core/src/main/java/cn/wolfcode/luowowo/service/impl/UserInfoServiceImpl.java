package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.UserInfo;
import cn.wolfcode.luowowo.exception.LogicException;
import cn.wolfcode.luowowo.redis.service.IUserInfoRedisService;
import cn.wolfcode.luowowo.repository.UserInfoRepository;
import cn.wolfcode.luowowo.service.IUserInfoService;
import cn.wolfcode.luowowo.util.AssertUtils;
import cn.wolfcode.luowowo.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
//@Transactional  暂时先别管事务
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private IUserInfoRedisService userInfoRedisService;


    public UserInfo get(String id) {
        Optional<UserInfo> optional = repository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }


    public boolean checkPhone(String phone) {
        //select * from userInfo where phone = ?
        UserInfo userInfo =
                repository.findByPhone(phone);
        return userInfo != null;
    }

    //思考:后端是否需要重复前端校验？　　必须要
    public void userRegist(String phone, String nickname, String password, String rpassword, String verifyCode) {
        //1:参数是否为空
        AssertUtils.hasLength(phone, "手机号码不能为空");
        AssertUtils.hasLength(nickname, "昵称不能为空");
        AssertUtils.hasLength(password, "密码不能为空");
        AssertUtils.hasLength(rpassword, "确认密码不能为空");
        AssertUtils.hasLength(verifyCode, "短信认证码不能为空");
        //2:两次输入密码是否一致
        AssertUtils.isEquals(password, rpassword, "两次输入密码不一致");
        //3:手机号码格式是否正确 @TODO  JAVA怎么执行手机号码正则表达式
        //4:手机号码是否已经注册
        if(this.checkPhone(phone)){
            throw new LogicException("手机号码已经被注册了");
        }
        //5:短信验证码是否正确 @TODO
        String code = userInfoRedisService.getVerifyCode(phone);

        if(code == null || !code.equalsIgnoreCase(verifyCode)){
            throw new LogicException("验证码失效或者错误");
        }



        //6:用户注册
        UserInfo info = new UserInfo();
        info.setNickname(nickname);
        info.setPhone(phone);
        info.setEmail("");
        info.setPassword(password);  //假装已经加密
        info.setGender(UserInfo.GENDER_SECRET);
        info.setLevel(1);
        info.setCity("");
        info.setHeadImgUrl("/images/default.jpg");
        info.setInfo("");
        info.setState(UserInfo.STATE_NORMAL);  //明确写明状态
        info.setId(null);

        //模拟逻辑出bug
        //UserInfo xx = null;
        //xx.getCity();

        repository.save(info);
    }

    public void sendVerifyCode(String phone, String smsUrl, String appkey) {
        //1:创建短信验证码
        String code = UUID.randomUUID().toString()
                 .replaceAll("-", "").substring(0, 4);
        //2:拼接一个短信
        StringBuilder sb = new StringBuilder(80);
        sb.append("您好, 您的注册验证码是:").append(code).append("请在").append(Consts.VERIFY_CODE_VAI_TIME)
                .append("分钟之内使用");
        //3:发送短信 : 假装发一个短信
        System.out.println(sb.toString());

        //真实的短信发送: 需要借助短信网关的api: 发起http请求
        //String url = "https://way.jd.com/HZXINXI/noticeSms?mobile=134133466666&content=【装修惠】装修季，即日起至8月20止在我公司交订金客户，即送全房瓷砖，资深设计师免费为您量身定制设计方案详询189855xxx1退订回T&sendTime=&appkey=dd1f7d99cd632060789a56cfaa3b77ce";

        //springmvc 提供专门用于发起http请求操作类
        /*RestTemplate restTemplate = new RestTemplate();

        //https://way.jd.com/HZXINXI/noticeSms?mobile={1}&content={2}&sendTime=&appkey={3}
        Map map = restTemplate.getForObject(smsUrl, Map.class,phone, sb.toString(), appkey );

        System.out.println(map);

        if(!map.get("code").equals("10000")){
            //短信发送失败
            throw new LogicException("短信发送失败");
        }*/
        //4:将验证码存到redis中
        userInfoRedisService.setVerifyCode(phone, code);
    }

    public UserInfo userLogin(String username, String password) {
        UserInfo userInfo = repository.findByPhoneAndPassword(username, password);
        if(userInfo == null){
            throw new LogicException("账号或密码错误");
        }
        userInfo.setPassword(null);  //不需要密码
        return userInfo;
    }

    @Override
    public List<UserInfo> list() {
        return repository.findAll();
    }

    @Override
    public List<UserInfo> findByCity(String keyword) {
        return repository.findByCity(keyword);
    }
}
