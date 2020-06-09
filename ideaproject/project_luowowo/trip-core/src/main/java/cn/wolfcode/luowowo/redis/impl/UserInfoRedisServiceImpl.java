package cn.wolfcode.luowowo.redis.impl;

import cn.wolfcode.luowowo.domain.UserInfo;
import cn.wolfcode.luowowo.redis.IUserInfoRedisService;
import cn.wolfcode.luowowo.redis.util.RedisKeys;
import cn.wolfcode.luowowo.util.Consts;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserInfoRedisServiceImpl implements IUserInfoRedisService {
    @Autowired
    private StringRedisTemplate template;

    @Override
    public void setVerifyCode(String phone, String code) {
        String key = RedisKeys.VERIFY_CODE.join(phone);
        String value = code;
        //添加到redis中
        template.opsForValue().set(key,value,RedisKeys.VERIFY_CODE.getTime(),TimeUnit.SECONDS);
    }

    @Override
    public String getVerifyCode(String phone) {
        String key = RedisKeys.VERIFY_CODE.join(phone);
        return template.opsForValue().get(key);
    }

    @Override
    public String setToken(UserInfo user) {
        //key:token
        String token = UUID.randomUUID().toString().replaceAll("-","");
        String key = RedisKeys.LOGIN_TOKEN.join(token);
        //value:user对象
        String value = JSON.toJSONString(user);
        template.opsForValue().set(key,value,RedisKeys.LOGIN_TOKEN.getTime(),TimeUnit.SECONDS);
        return token;
    }

    @Override
    public UserInfo getUserByToken(String token) {
        if (!StringUtils.hasLength(token)){
            return null;
        }
        String key = RedisKeys.LOGIN_TOKEN.join(token);
        String userStr = template.opsForValue().get(key);
        if (StringUtils.hasLength(userStr)){
            //延迟token时间
            template.expire(token,RedisKeys.LOGIN_TOKEN.getTime(),TimeUnit.SECONDS);
            return JSON.parseObject(userStr,UserInfo.class);
        }
        return null;
    }
}
