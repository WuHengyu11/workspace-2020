package cn.wolfcode.domain;

import lombok.*;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor@ToString
public class Permission {
    private Long id;

    private String name;

    private String expression;


}