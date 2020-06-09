package cn.wolfcode.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class Employee {
    private Long id;
    private String name;
    private String password;
    private String email;
    private Integer age;
    private boolean admin;
    private Department dept;
    private List<Role> roles = new ArrayList<>();//员工拥有的角色集合
}