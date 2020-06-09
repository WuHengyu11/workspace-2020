package com.wolfcode.mongodb.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JsonResult {
    private boolean success = true;
    private String msg;
    //数据属性,当结果返回时需要状态时使用data字段来接受数据
    private Object data;

    public JsonResult(){

    }

    public JsonResult(boolean success,String msg){
        this.success = success;
        this.msg = msg;
    }
    //表示成功,同时返回操作成功的数据
    public JsonResult(Object data){
        this.success = true;
        this.data = data;
    }
}
