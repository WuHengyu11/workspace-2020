package cn.wolfcode.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

@Getter@Setter@ToString
public class Salary {
    private Long id;
    private Long money;
    private Integer year;
    private Integer month;
    private Employee employee;
    private SystemDictionaryItem payout;
    public String getJson(){
        HashMap map = new HashMap();
        map.put("id",id);
        map.put("money",money);
        map.put("year",year);
        map.put("month",month);
        if (employee != null) {
            map.put("employee",employee.getId());
        }
        if (payout != null) {
            map.put("payout",payout.getId());
        }
        return JSON.toJSONString(map);
    }
}