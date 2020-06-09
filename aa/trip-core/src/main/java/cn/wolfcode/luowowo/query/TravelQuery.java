package cn.wolfcode.luowowo.query;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TravelQuery extends  QueryObject{
    private String destId;

    private int orderType = 1;  //默认最新排序

    private int perExpendType = -1;

    private int dayType = -1;

    //2  --->   new TravelCondition(4, 7)
    public TravelCondition getDay(){
        return TravelCondition.MAP_DAY.get(dayType);
    }

    //2  --->  new TravelCondition(1000, 6000)
    public TravelCondition getPerExpend(){
        return TravelCondition.MAP_PEREXPEND.get(perExpendType);
    }


}
