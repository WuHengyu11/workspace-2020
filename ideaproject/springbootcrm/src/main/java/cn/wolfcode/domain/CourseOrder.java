package cn.wolfcode.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

@Data
public class CourseOrder {
    private Long id;//编号
    private String inputTime;//销售时间
    private Customer customer;//客户
    private SystemDictionaryItem course;//购买课程
    private BigDecimal money;//销售金额

    //回显
    public String getJson(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",id);
        if(customer != null){
            map.put("customerId",customer.getId());
        }
        if (course != null) {
            map.put("courseId",course.getId());
        }
        map.put("money",money);
        return JSON.toJSONString(map);
    }
}