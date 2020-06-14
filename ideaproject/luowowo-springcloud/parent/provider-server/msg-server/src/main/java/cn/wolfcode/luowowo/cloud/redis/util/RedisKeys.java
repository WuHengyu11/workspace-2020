package cn.wolfcode.luowowo.cloud.redis.util;

import lombok.Getter;

/**
 * 系统redis key的管理类
 * 每一枚举实例就是一个redis key
 */
@Getter
public enum RedisKeys {
    //短信验证码在redis的key实例
    VERIFY_CODE("verify_code", 5 * 60L);
    private String prefix; //key的前缀
    private Long time;     //key的有效时间 单位:秒

    private RedisKeys(String prefix,Long time){
        this.prefix = prefix;
        this.time = time;
    }

    //key的拼接
    public String join(String ... values){
        StringBuilder sb = new StringBuilder(80);
        sb.append(this.prefix);
        for (String s : values){
            sb.append(":").append(s);
        }

        return sb.toString();
    }
}
