package cn.wolfcode.luowowo.cloud.common;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class BusinessException extends RuntimeException{
    private CodeMsg codeMsg;
    public BusinessException(){};
    public BusinessException(CodeMsg codeMsg){
        this.codeMsg = codeMsg;
    };
}
