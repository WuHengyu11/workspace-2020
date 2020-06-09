package cn.wolfcode.managesystem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class Department extends Employee{
	private Long id;//部门序号
	private String dep_name;//部门名称
	private String dep_id;//部门编号
}
