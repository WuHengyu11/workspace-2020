package cn.wolfcode.luowowo.cloud.service;

public interface IUserSevice {
    Boolean checkPhone(String phone);

    void regist(String phone, String nickname, String password, String rpassword);
}

