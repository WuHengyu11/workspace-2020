package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.UserInfo;

import java.util.Optional;

public interface IUserInfoService {
    /**
     * 获取单个
     * @param id
     * @return
     */
    UserInfo get(String id);

    /**
     * 检查手机号码是否存在
     * @param phone
     * @return true:手机号码存在 false:手机号码不存在
     */
    boolean checkPhone(String phone);

    /**
     * 发送短信方法
     * @param phone
     * @param smsUrl
     * @param smsAppkey
     */
    void sendVerifyCode(String phone, String smsUrl, String smsAppkey);

    /**
     * 用户注册
     * @param phone
     * @param nickname
     * @param password
     * @param rpassword
     * @param verifyCode
     */
    void regist(String phone, String nickname, String password, String rpassword, String verifyCode);

    /**
     * 用户登录
     * @param username
     * @param password
     */
    UserInfo login(String username, String password);
}
