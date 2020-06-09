package cn.wolfcode.javaweb2.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class Employee {
	private Long id; //员工id
	private String name; //员工姓名
	private String email; //员工邮箱
	private Integer age; //员工年龄
	private BigDecimal salary; //员工薪水
	private Date birthday ; //员工生日
	
}
