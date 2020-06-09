package cn.wolfcode.qo;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserQueryObject extends QueryObject{
    private String keyword;
    private Integer minAge;
    private Integer maxAge;
}
