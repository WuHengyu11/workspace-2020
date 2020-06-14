package cn.wolfcode.luowowo.cloud.redis;


/**
 * 用户缓存接口
 */
public interface IRedisService {
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

}
