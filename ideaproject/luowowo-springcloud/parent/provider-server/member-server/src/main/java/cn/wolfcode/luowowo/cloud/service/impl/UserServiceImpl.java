package cn.wolfcode.luowowo.cloud.service.impl;

import cn.wolfcode.luowowo.cloud.common.BusinessException;
import cn.wolfcode.luowowo.cloud.domain.UserInfo;
import cn.wolfcode.luowowo.cloud.repository.UserInfoRepository;
import cn.wolfcode.luowowo.cloud.service.IUserSevice;
import cn.wolfcode.luowowo.cloud.util.AssertUtil;
import cn.wolfcode.luowowo.cloud.web.msg.MemberCodeMsg;
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
        System.out.println(userInfo);
        System.out.println("666666666");
        return userInfo != null;
    }

    @Override
    public void regist(String phone, String nickname, String password, String rpassword) {
        //判断参数是否为空
        AssertUtil.hasLength(phone,MemberCodeMsg.PHONE_EMPTY_ERROR);
        AssertUtil.hasLength(nickname,MemberCodeMsg.NICKNAME_EMPTY_ERROR);
        AssertUtil.hasLength(password,MemberCodeMsg.PASSWORD_EMPTY_ERROR);
        AssertUtil.hasLength(rpassword,MemberCodeMsg.RPASSWORD_EMPTY_ERROR);
        //判断两次密码是否一致
        AssertUtil.isEquals(password,rpassword,MemberCodeMsg.PASSWORD_COMPARE_ERROR);
        //手机号码是否已经被注册
        if (this.checkPhone(phone)){
            throw new BusinessException(MemberCodeMsg.PHONE_EXIST_ERROR);
        }

        //注册逻辑
        UserInfo userInfo = new UserInfo();
        userInfo.setNickname(nickname);
        userInfo.setPhone(phone);
        userInfo.setEmail("");
        userInfo.setPassword(password);
        userInfo.setLevel(1);
        userInfo.setCity("内蒙古");
        userInfo.setHeadImgUrl("/images/default.jpg");
        userInfo.setInfo("");
        userInfo.setState(UserInfo.STATE_NORMAL);
        userInfoRepository.save(userInfo);
    }
}
