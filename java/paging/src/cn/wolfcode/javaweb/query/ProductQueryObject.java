package cn.wolfcode.javaweb.query;

import java.math.BigDecimal;

import cn.wolfcode.javaweb.util.StringUtil;

//将分页查询的参数和过滤查询的参数合并到一起
public class ProductQueryObject extends QueryObject {
	private String productName;
	private BigDecimal minSalePrice;
	private BigDecimal maxSalePrice;
	
	public String getProductName(){
		return StringUtil.hasLength(productName) ? productName : null;
	}
}
