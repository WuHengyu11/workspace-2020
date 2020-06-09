package cn.wolfcode.qo;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class HeroQueryObject extends QueryObject{
    private String keyword;
    private Integer minHp;
    private Integer maxHp;
}
