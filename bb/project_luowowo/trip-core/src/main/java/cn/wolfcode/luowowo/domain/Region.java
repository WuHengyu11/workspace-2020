package cn.wolfcode.luowowo.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 区域
 */
@Setter
@Getter
@Document("destination_region")
public class Region extends BaseDomain {
    public static final int STATE_HOT = 1;
    public static final int STATE_NORMAL = 0;

    private String name;        //地区名
    private String sn;          //地区编码
    private List<String> refIds = new ArrayList<>();     //关联的id
    private int ishot = STATE_NORMAL;         //是否为热点
    private int sequence;   //序号
    private String info;  //简介


    public String getJsonString(){
        Map<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        map.put("sn",sn);
        map.put("refIds",getRefIds());
        map.put("ishot",ishot);
        map.put("sequence",sequence);
        map.put("info",info);
        return JSON.toJSONString(map);
    }
}