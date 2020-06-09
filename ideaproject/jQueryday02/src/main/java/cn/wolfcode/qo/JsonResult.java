package cn.wolfcode.qo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Getter@Setter
public class JsonResult {
    private String msg;
    private boolean success;
}
