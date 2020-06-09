package cn.wolfcode.luowowo.redis.util;

import cn.wolfcode.luowowo.util.Consts;
import lombok.Getter;

/**
 * 系统redis key的管理类
 * 每一枚举实例就是一个redis key
 */
@Getter
public enum RedisKeys {
    //攻略点赞key对象
    STRATEGY_THUMBUP("strategy_thumbup",-1L),
    //用户攻略收藏集合key实例
    USER_STRATEGY_FAVOR("user_strategy_favor",-1L),//约定-1不设置超时时间
    //攻略统计vo对象key实例
    STRATEGY_STATIS_VO("strategy_statis_vo",-1L), //约定-1不设置超时时间
    //短信验证码在redis的key实例
    VERIFY_CODE("verify_code", Consts.VERIFY_CODE_VAI_TIME * 60L),
    LOGIN_TOKEN("login_token", Consts.USER_INFO_TOKEN_VAI_TIME * 60L);
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
