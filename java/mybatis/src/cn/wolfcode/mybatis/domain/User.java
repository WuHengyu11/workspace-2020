package cn.wolfcode.mybatis.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
* @author 作者: 邬恒宇
* @version 创建时间：2020年2月28日 下午9:23:12
*/
@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class User {
	public User(Object object, String string, int i, BigDecimal bigDecimal, Date date) {
		// TODO Auto-generated constructor stub
	}
	private Long id;
	private String name;
	private Integer age;
	private BigDecimal salary;
	private Date hiredate;
}
