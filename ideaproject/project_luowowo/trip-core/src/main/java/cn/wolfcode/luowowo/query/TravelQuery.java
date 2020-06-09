package cn.wolfcode.luowowo.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelQuery extends QueryObject{
    private String destId;//目的地id
    private int dayType = -1;//旅游天数
    private int perExpendType = -1; //人均消费
    private int orderType = 1; //最新的
    public String getOrderBy(){
        return orderType == 1 ? "createTime" : "viewnum";
    }
    //将dayType装换成TravelCondition对象
    public TravelCondition getDay(){
        return TravelCondition.DAY_MAP.get(dayType);
    }

    public TravelCondition getPerExpend(){
        return TravelCondition.PEREXPEND_MAP.get(perExpendType);
    }
}
