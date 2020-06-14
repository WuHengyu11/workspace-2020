package cn.wolfcode.luowowo.cloud.redis.impl;

import cn.wolfcode.luowowo.cloud.redis.IRedisService;
import cn.wolfcode.luowowo.cloud.redis.util.RedisKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements IRedisService {
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

}
