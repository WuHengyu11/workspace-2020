package cn.wolfcode.luowowo.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 攻略分类： 从属于某个目的地
 */
@Setter
@Getter
@Document("strategy_catalog")
public class StrategyCatalog extends BaseDomain {

    public static final int STATE_NORMAL = 0;  //显示
    public static final int STATE_DISABLE = 1;  //禁用

    private String destId;  //目的地
    private String destName;    //目的名称
    private int state = STATE_NORMAL;  //状态
    private String name;  //分类名
    private int sequence; //排序

    @Transient
    private List<Strategy> strategies = new ArrayList<>();
    public String getStateDisplay(){
        return state == STATE_NORMAL ? "正常" : "禁用";
    }

    public String getJsonString(){
        Map<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        map.put("state",state);
        map.put("destId",destId);
        map.put("sequence",sequence);
        return JSON.toJSONString(map);
    }
}