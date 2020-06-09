package cn.wolfcode.domain;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.util.HashMap;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class Department {
    private Long id;
    private String name;
    private String sn;

    //获取当前部门对象对应得字符串
    public String getJson(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        map.put("sn",sn);
        return JSON.toJSONString(map);
    }
}