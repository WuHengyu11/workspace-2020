package cn.wolfcode.luowowo.cloud.web.msg;

import cn.wolfcode.luowowo.cloud.common.CodeMsg;
import cn.wolfcode.luowowo.cloud.common.Result;
import org.springframework.web.bind.annotation.ResponseBody;

public class WebsiteCodeMsg extends CodeMsg {
    private WebsiteCodeMsg(Integer code,String msg){
        super(code,msg);
    }
   public static final WebsiteCodeMsg DEFAULT_ERROR = new WebsiteCodeMsg(500100,"聚合服务繁忙");
   public static final WebsiteCodeMsg MEMBER_SERVER_ERROR = new WebsiteCodeMsg(500101,"会员服务繁忙");
}