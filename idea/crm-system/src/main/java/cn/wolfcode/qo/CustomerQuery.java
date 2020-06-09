package cn.wolfcode.qo;

import lombok.Data;

@Data
public class CustomerQuery extends QueryObject {
    private Integer status;//状态
    private Long sellerId = -1L;//销售员
    private String keyword;
}
