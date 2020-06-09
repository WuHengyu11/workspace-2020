package cn.wolfcode.qo;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

public class JsonResult {
    private String msg;
    private boolean success;

    public JsonResult() {
    }

    public JsonResult(String msg, boolean success) {
        this.msg = msg;
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
