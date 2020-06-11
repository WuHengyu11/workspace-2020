package cn.wolfcode.luowowo.cloud.common;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor

public class Result<T> {
    public static final Integer DEFAULT_SUCCESS_CODE = 200;
    public static final String DEFAULT_SUCCESS_MSG = "操作成功";
    private Integer code;//状态码
    private String msg;//错误信息
    private T data;//数据

    public Result(Integer code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;

    }
    public static <T> Result<T> success(T data){
        return new Result(DEFAULT_SUCCESS_CODE,DEFAULT_SUCCESS_MSG,data);
    }

    public static <T> Result success(String msg,T data){
        return new Result(DEFAULT_SUCCESS_CODE,msg,data);
    }

    public static Result error(CodeMsg codeMsg){
        return new Result(codeMsg.getCode(),codeMsg.getMsg(),null);
    }
}
