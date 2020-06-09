package cn.wolfcode.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class SystemDictionary {
    private Long id;

    private String sn;

    private String title;

    private String intro;

    public String getJson(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("title",title);
        map.put("intro",intro);
        map.put("sn",sn);
        return JSON.toJSONString(map);
    }
}