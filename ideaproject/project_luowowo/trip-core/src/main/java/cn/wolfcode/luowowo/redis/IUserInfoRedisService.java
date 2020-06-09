package cn.wolfcode.luowowo.redis;

import cn.wolfcode.luowowo.domain.UserInfo;

/**
 * 用户缓存接口
 */
public interface IUserInfoRedisService {
    /**
     * 设置手机验证码
     * @param phone
     * @param code
     */
    void setVerifyCode(String phone, String code);

    /**
     * 获取手机验证码
     * @param phone
     * @return
     */
    String getVerifyCode(String phone);

    /**
     * 将登录用户设置到redis
     * @param user
     * @return
     */
    String setToken(UserInfo user);

    /**
     * 通过token获取用户信息
     * @param token
     * @return
     */
    UserInfo getUserByToken(String token);
}
