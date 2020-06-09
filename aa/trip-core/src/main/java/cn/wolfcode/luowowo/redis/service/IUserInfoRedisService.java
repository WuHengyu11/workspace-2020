package cn.wolfcode.luowowo.redis.service;

import cn.wolfcode.luowowo.domain.UserInfo; /**
 * 用户缓存服务接口
 */
public interface IUserInfoRedisService {

    /**
     * 验证码设置
     * @param phone
     * @param code
     */
    void setVerifyCode(String phone, String code);

    /**
     * 获取验证码
     * @param phone
     * @return
     */
    String getVerifyCode(String phone);

    /**
     * 设置用户token值
     * @param token
     * @param userInfo
     */
    void setToken(String token, UserInfo userInfo);

    /**
     * 通过token获取用户信息
     * @param token
     * @return
     */
    UserInfo getUserByToken(String token);
}
