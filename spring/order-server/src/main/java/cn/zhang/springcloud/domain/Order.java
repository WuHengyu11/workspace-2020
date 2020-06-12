package cn.zhang.springcloud.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class Order implements Serializable {
    private String orderNo;
    private Date createTime;
    private String productName;
    private int productPrice;
    private Long userId;
}