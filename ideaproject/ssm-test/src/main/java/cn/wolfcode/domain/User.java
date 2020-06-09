package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class User {
    private Long id;
    private String username;
    private String email;
    private Integer age;

}