package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.UserInfo;

import java.util.List;

/**
 * 用户服务接口
 */
public interface IUserInfoService  {

    /**
     * 查单个
     * @param id
     * @return
     */
    UserInfo get(String id);

    /**
     * 检查用户手机号码是否存在
     * @param phone
     * @return true: 手机号码存在  false: 手机号码不存在
     */
    boolean checkPhone(String phone);

    /**
     * 用户注册
     * @param phone
     * @param nickname
     * @param password
     * @param rpassword
     * @param verifyCode
     */
    void userRegist(String phone, String nickname, String password, String rpassword, String verifyCode);

    /**
     * 短信发送
     * @param phone
     * @param smsUrl
     * @param appkey
     */
    void sendVerifyCode(String phone, String smsUrl, String appkey);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    UserInfo userLogin(String username, String password);

    /**
     * 查多个
     * @return
     */
    List<UserInfo> list();

    /**
     * 用户city查询
     * @param keyword
     * @return
     */
    List<UserInfo> findByCity(String keyword);
}
