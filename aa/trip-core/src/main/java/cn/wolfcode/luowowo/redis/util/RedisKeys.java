package cn.wolfcode.luowowo.redis.util;

import cn.wolfcode.luowowo.util.Consts;
import lombok.Getter;

/**
 * 统一redis 的key管理
 */
@Getter
public enum RedisKeys {

    //api接口防刷key对象实例
    BRUSH_PROOF("brush_proof", 60l),


    //用户攻略点赞标签key对象实例
    STRATEGY_THUMBUP("strategy_thumbup", -1l),  //-1表示不设定超时


    //用户攻略收藏统计的key对象实例
    USER_STRATEGY_FAVOR("user_strategy_favor", -1l),  //-1表示不设定超时


    //攻略统计的key对象实例
    STRATEGY_STATIS_VO("strategy_statis_vo", -1l),  //-1表示不设定超时

    //用户登录key对象实例
    USER_LOGIN_TOKEN("user_login_token", Consts.USER_INFO_TOKEN_VAI_TIME * 60L),

    //短信验证码key对象实例
    VERIFY_CODE("verify_code", Consts.VERIFY_CODE_VAI_TIME * 60L);

    private String prefix;  //redis的key的前缀
    private Long time;      //redis的key的有效时间, 约定单位是 s

    private RedisKeys(String prefix, Long time){
        this.prefix = prefix;
        this.time = time;
    }
    //拼接 redis的key
    public String join(String ... values){
        StringBuilder sb = new StringBuilder(50);
        sb.append(this.prefix);
        for (String v : values) {
            sb.append(":").append(v);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        //user:xxx:post:xx
        //verify_code:13700000000
        System.out.println(RedisKeys.VERIFY_CODE.join("13700000000"));
        //verify_code:13700000000:xx:yy:zz
        System.out.println(RedisKeys.VERIFY_CODE.join("13700000000", "xx","yy","zz"));
    }



}
