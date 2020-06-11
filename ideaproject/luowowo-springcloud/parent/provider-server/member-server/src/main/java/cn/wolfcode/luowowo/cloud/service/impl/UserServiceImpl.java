package cn.wolfcode.luowowo.cloud.service.impl;

import cn.wolfcode.luowowo.cloud.domain.UserInfo;
import cn.wolfcode.luowowo.cloud.repository.UserInfoRepository;
import cn.wolfcode.luowowo.cloud.service.IUserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserServiceImpl implements IUserSevice {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Transactional
    @Override
    public Boolean checkPhone(String phone) {
        UserInfo userInfo = userInfoRepository.findByPhone(phone);
        return userInfo != null;
    }
}
