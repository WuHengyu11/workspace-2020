package cn.wolfcode._04_mybatis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private Long id;

    private String name;

    private Integer age;


}