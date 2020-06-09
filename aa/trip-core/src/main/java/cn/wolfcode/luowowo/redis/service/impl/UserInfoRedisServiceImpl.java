package cn.wolfcode.luowowo.redis.service.impl;

import cn.wolfcode.luowowo.domain.UserInfo;
import cn.wolfcode.luowowo.redis.service.IUserInfoRedisService;
import cn.wolfcode.luowowo.redis.util.RedisKeys;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service
public class UserInfoRedisServiceImpl implements IUserInfoRedisService {

    @Autowired
    private StringRedisTemplate template;

    public void setVerifyCode(String phone, String code) {
        //String key = "verify_code:" + phone;
        String key = RedisKeys.VERIFY_CODE.join(phone);
        //参数1: key , 参数2:  value 参数3:时间长度  参数4:时间单位
        template.opsForValue().set(key, code, RedisKeys.VERIFY_CODE.getTime(), TimeUnit.SECONDS);
    }

    public String getVerifyCode(String phone) {
        String key = RedisKeys.VERIFY_CODE.join(phone);
        return template.opsForValue().get(key);
    }

    public void setToken(String token, UserInfo userInfo) {
        //user_login_token:sdjfksdjfjskdfkd
        String key = RedisKeys.USER_LOGIN_TOKEN.join(token);
        String value = JSON.toJSONString(userInfo);
        //将用户信息设置到redis中, 有效时间为30分钟
        template.opsForValue().set(key, value, RedisKeys.USER_LOGIN_TOKEN.getTime(), TimeUnit.SECONDS);
    }

    public UserInfo getUserByToken(String token) {
        if(!StringUtils.hasLength(token)){
            return null;
        }
        String key = RedisKeys.USER_LOGIN_TOKEN.join(token);
        if(template.hasKey(key)){
            //延长key时间
            template.expire(key, RedisKeys.USER_LOGIN_TOKEN.getTime(), TimeUnit.SECONDS);
            //获取用户
            String userStr = template.opsForValue().get(key);
            return JSON.parseObject(userStr, UserInfo.class);
        }
        return null;
    }
}
