package cn.wolfcode.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;

/**
 * 客户
 */
@Data
public class Customer {
    public static final int STATUS_COMMON = 0;//潜在客户
    public static final int STATUS_NORMAL = 1;//正式客户
    public static final int STATUS_FAIL = 2;//开发失败
    public static final int STATUS_LOST = 3;//流失客户
    public static final int STATUS_POOL = 4;//客户池
    private Long id;
    private String name;//姓名
    private Integer age;//年龄
    private Integer gender;//性别
    private String tel;//电话
    private String qq;//qq
    private SystemDictionaryItem job;//职业
    private SystemDictionaryItem source;//来源
    private Employee seller;//销售人员
    private Employee inputUser;//录入人
    private Date inputTime;//录入时间
    private Integer status = STATUS_COMMON;//状态
    public String getJson(){
        HashMap map = new HashMap();
        map.put("id",id);
        map.put("name",name);
        map.put("age",age);
        map.put("gender",gender);
        map.put("tel",tel);
        map.put("qq",qq);
        if(job!=null){
            map.put("jobId",job.getId());
        }
        if(source!=null){
            map.put("sourceId",source.getId());
        }
        if(seller!=null){
            map.put("sellerId",seller.getId());
            map.put("sellerName",seller.getName());
        }
        return JSON.toJSONString(map);
    }
    public String getStatusName(){
        switch (status){
            case STATUS_NORMAL:
                return "正式客户";
            case  STATUS_FAIL:
                return "开发失败客户";
            case STATUS_LOST:
                return "流失客户";
            case STATUS_POOL:
                return "客户池";
            default:
                return "潜在客户";
        }
    }
}