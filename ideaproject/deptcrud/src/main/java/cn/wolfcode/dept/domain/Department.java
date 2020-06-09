package cn.wolfcode.dept.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class Department {
    private Long id;//部门ID
    private String name;//部门名称
    private String sn;//部门别名
}
