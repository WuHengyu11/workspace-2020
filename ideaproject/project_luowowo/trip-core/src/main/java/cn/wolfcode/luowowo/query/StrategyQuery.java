package cn.wolfcode.luowowo.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StrategyQuery extends QueryObject{
    private String destId;//目的地id
    private String themeId;//主题id
}
