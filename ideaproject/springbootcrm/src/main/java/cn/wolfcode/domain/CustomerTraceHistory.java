package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Setter
@Getter
public class CustomerTraceHistory {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")//告诉springmvc,安装pattern规定的格式进行解析
    private Date traceTime;//跟进时间
    private String traceDetails;//跟进内容
    private SystemDictionaryItem traceType;//跟进方式
    private Integer traceResult;//跟进结果
    private Customer customer;//客户
    private Employee inputUser;//录入人
    private Date inputTime;//录入时间
}