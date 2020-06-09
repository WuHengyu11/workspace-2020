package cn.wolfcode.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class SystemDictionaryItem {
    private Long id;

    private Long parentId;

    private String title;

    private Integer sequence;
    public String getJson(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("title",title);
        map.put("parentId",parentId);
        map.put("sequence",sequence);
        return JSON.toJSONString(map);
    }

}