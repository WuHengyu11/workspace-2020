package cn.wolfcode.domain;

import lombok.*;

@Setter@Getter@ToString
@AllArgsConstructor@NoArgsConstructor
public class Permission {
    private Long id;
    private String name;
    private String expression;
}