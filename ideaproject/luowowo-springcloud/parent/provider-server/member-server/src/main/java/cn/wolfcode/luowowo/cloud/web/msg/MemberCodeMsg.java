package cn.wolfcode.luowowo.cloud.web.msg;

import cn.wolfcode.luowowo.cloud.common.CodeMsg;

public class MemberCodeMsg extends CodeMsg {
    private MemberCodeMsg(Integer code,String msg){
        super(code,msg);
    }
    public static final MemberCodeMsg DEFAULT_ERROR = new MemberCodeMsg(500200,"会员服务繁忙");
}
