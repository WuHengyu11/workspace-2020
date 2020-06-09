package cn.wolfcode.employeetest.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class Employee {
	private Long id;//员工编号
	private String username;//员工姓名
	private String password;//员工密码
	private String email;//员工邮箱
	private Integer age;//员工年龄
	private BigDecimal salary;//员工薪水
}
