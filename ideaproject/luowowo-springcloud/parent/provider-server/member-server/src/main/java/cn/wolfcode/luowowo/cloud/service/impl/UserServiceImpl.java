package cn.wolfcode.luowowo.cloud.service.impl;

import cn.wolfcode.luowowo.cloud.domain.UserInfo;
import cn.wolfcode.luowowo.cloud.repository.UserInfoRepository;
import cn.wolfcode.luowowo.cloud.service.IUserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service

public class UserServiceImpl implements IUserSevice {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Transactional
    @Override
    public Boolean checkPhone(String phone) {
        UserInfo userInfo = userInfoRepository.findByPhone(phone);
        System.out.println("666666666");
        if (phone.equals(userInfo.getPhone())){
            throw new RuntimeException("手机号已存在");
        }
        return userInfo != null;
    }
}
