package cn.wolfcode.luowowo.cloud.web.msg;

import cn.wolfcode.luowowo.cloud.common.CodeMsg;
import cn.wolfcode.luowowo.cloud.util.AssertUtil;

public class MemberCodeMsg extends CodeMsg {
    private MemberCodeMsg(Integer code, String msg){
        super(code,msg);
    }
    public static final MemberCodeMsg DEFAULT_ERROR = new MemberCodeMsg(500200,"会员服务繁忙");
    public static final CodeMsg PHONE_EXIST_ERROR = new MemberCodeMsg(500201,"手机号码已注册");
    public static final CodeMsg PARAM_NULL_ERROR = new MemberCodeMsg(500202,"参数不能为空");
    public static final CodeMsg PHONE_EMPTY_ERROR = new MemberCodeMsg(500203,"手机号码不能为空");
    public static final CodeMsg NICKNAME_EMPTY_ERROR = new MemberCodeMsg(500204,"昵称不能为空");
    public static final CodeMsg PASSWORD_EMPTY_ERROR = new MemberCodeMsg(500205,"密码不能为空");
    public static final CodeMsg RPASSWORD_EMPTY_ERROR = new MemberCodeMsg(500206,"确认密码不能为空");
    public static final CodeMsg PASSWORD_COMPARE_ERROR = new MemberCodeMsg(500207,"两次输入的密码不一致");
}
