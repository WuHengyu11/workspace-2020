package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class Team {
    private Long id;//团队编号
    private String name;//团队名称
    private String abbr;//团队简称
}