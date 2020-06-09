package cn.wolfcode.luowowo.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * 游记推荐
 */
@Setter
@Getter
@Document("banner")
public class Banner  extends BaseDomain {
    public static final int STATE_NORMAL = 0;   //正常
    public static final int STATE_DISABLE = 1;  //禁用

    public static final int TYPE_TRAVEL = 1;  //游记
    public static final int TYPE_STRATEGY = 2;  //攻略

    private String refId;  //关联id

    private String title;  //标题

    private String subTitle; //副标题

    private String coverUrl; //封面

    private int state = STATE_NORMAL; //状态

    private int sequence; //排序

    private int type;

    public String getJsonString(){
        Map<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("title",title);
        map.put("subTitle",subTitle);
        map.put("coverUrl",coverUrl);
        map.put("state",state);
        map.put("sequence",sequence);
        map.put("refId",refId);
        map.put("type",type);
        return JSON.toJSONString(map);
    }

    public String getStateDisplay(){
        return state == STATE_NORMAL?"正常":"禁用";
    }
    public String getTypeDisplay(){
        return type == TYPE_STRATEGY?"攻略":"游记";
    }

}