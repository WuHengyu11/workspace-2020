package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString@Getter@Setter
public class Employee {
    private Long id;
    private String name;
    private String password;
    private String email;
    private Integer age;
    private Boolean admin;
    private Long deptId;
}