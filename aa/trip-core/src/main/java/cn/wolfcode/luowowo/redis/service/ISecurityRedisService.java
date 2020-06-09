package cn.wolfcode.luowowo.redis.service;


/**
 * 安全防护接口
 */
public interface ISecurityRedisService {


    /**
     * 判断是否允许访问
     * @param key
     * @return true：允许访问 fasle 不允许访问
     */
    boolean isAllowBrush(String key);
}
