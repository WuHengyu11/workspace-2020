package cn.wolfcode.javaweb.query;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ProductQueryObject extends QueryObject {
	private String productName;//商品名称
	private BigDecimal minPrice;//商品最低售价
	private BigDecimal maxPrice;//商品最高售价
}
