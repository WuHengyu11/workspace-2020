package cn.wolfcode.managesystem.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 员工信息
 * @author Administrator
 *
 */
@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class Employee {
	private Long id;//员工编号
	private String name;//员工姓名
	private String email;//员工邮箱
	private Integer age;//员工年龄
	private BigDecimal salary;//员工薪资
	private String img_path;//员工照片路径
	private String dep_id;//员工所属部门编号
}
