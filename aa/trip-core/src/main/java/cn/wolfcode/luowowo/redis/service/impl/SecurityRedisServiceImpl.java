package cn.wolfcode.luowowo.redis.service.impl;

import cn.wolfcode.luowowo.redis.service.ISecurityRedisService;
import cn.wolfcode.luowowo.redis.util.RedisKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class SecurityRedisServiceImpl implements ISecurityRedisService {


    @Autowired
    private StringRedisTemplate template;

    @Override
    public boolean isAllowBrush(String key) {

        //方式1：
        //判断指定key是否存在
        //如果存在， 访问次数减1， 判断减一之后结果是否小于0，小于表示超频
        //如果不存在， 设置一下访问的频率10， 然后减一


        //方式2：
        //如果有不做 任何操作，如果没有添加
        //等价于： setnx命令
        template.opsForValue().setIfAbsent(key, "10", RedisKeys.BRUSH_PROOF.getTime(), TimeUnit.SECONDS);
        //减1
        Long decrement = template.opsForValue().decrement(key);
        return decrement >= 0;
    }
}
