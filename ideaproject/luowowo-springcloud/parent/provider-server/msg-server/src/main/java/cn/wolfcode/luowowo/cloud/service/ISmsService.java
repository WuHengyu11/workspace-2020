package cn.wolfcode.luowowo.cloud.service;

public interface ISmsService {
    void sendVerifyCode(String phone);

    boolean checkVerifyCode(String phone, String verifyCode);
}
