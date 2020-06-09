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
 * 目的地(行政地区：国家/省份/城市)
 */
@Setter
@Getter
@Document("destination")
public class Destination extends BaseDomain {
    private String name;        //名称
    private String english;  //英文名
    private String parentId; //上级目的地
    private String parentName;  //上级目的名
    private String info;    //简介
    private int deep;
    private  String coverUrl;

    //子地区
    @Transient   //添加操作时,忽略的属性,操作时不会修改表
    private List<Destination> children = new ArrayList<>();

    public String getJsonString(){
        Map<String,Object> map = new HashMap<>();
        map.put("id", super.getId());
        map.put("info", this.info);

        return JSON.toJSONString(map);
    }
}