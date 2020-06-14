package cn.wolfcode.luowowo.cloud.service.impl;

import cn.wolfcode.luowowo.cloud.common.BusinessException;
import cn.wolfcode.luowowo.cloud.service.ISmsService;
import cn.wolfcode.luowowo.cloud.redis.IRedisService;
import cn.wolfcode.luowowo.cloud.web.msg.MsgCodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SmsServiceImpl implements ISmsService {
    @Autowired
    private IRedisService redisService;
    @Override
    public void sendVerifyCode(String phone) {
        //创建短信验证码:4位
        String code = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 4);
        //创建短信内容
        StringBuilder stringBuilder = new StringBuilder(80);
        stringBuilder.append("【创信】您注册的短信验证码:").append(code).append(",请在5分钟内使用!");
        System.out.println(stringBuilder.toString());

        //将验证码存入到Redis
        redisService.setVerifyCode(phone,code);
    }

    @Override
    public boolean checkVerifyCode(String phone, String verifyCode) {
        String code = redisService.getVerifyCode(phone);
        if (code == null || !code.equalsIgnoreCase(verifyCode)){
            throw new BusinessException(MsgCodeMsg.VERIFYCODE_ERROR);
        }
        return true;
    }
}
