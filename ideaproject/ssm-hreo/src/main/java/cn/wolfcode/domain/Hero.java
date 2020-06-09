package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class Hero {
    private Long id;
    private String name;
    private String nickname;
    private Integer hp;
    private Integer mp;
}