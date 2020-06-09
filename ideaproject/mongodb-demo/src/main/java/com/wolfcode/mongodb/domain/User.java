package com.wolfcode.mongodb.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter@ToString
@Document("user") //设置文档所在的集合
public class User {
    @Id //文档的id使用ObjectId类型来封装,并且贴上@Id注解
    private String _id;//如果使用字符串类型,贴上@Id之后spring-data会自动将字符串类型转换成ObjectId
    private String name;
    private Integer age;
    private List<String> hobby = new ArrayList<>();
}
