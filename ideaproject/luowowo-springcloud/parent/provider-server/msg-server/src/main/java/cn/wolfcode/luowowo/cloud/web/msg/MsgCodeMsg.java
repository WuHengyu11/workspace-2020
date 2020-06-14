package cn.wolfcode.luowowo.cloud.web.msg;

import cn.wolfcode.luowowo.cloud.common.CodeMsg;

public class MsgCodeMsg extends CodeMsg {
    private MsgCodeMsg(Integer code, String msg){
        super(code,msg);
    }
    public static final MsgCodeMsg DEFAULT_ERROR = new MsgCodeMsg(500300,"消息服务繁忙");
    public static final MsgCodeMsg VERIFYCODE_ERROR = new MsgCodeMsg(500301,"验证码有误");
}

