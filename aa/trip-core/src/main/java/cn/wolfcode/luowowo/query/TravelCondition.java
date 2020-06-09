package cn.wolfcode.luowowo.query;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 游记查询范围条件
 */
@Getter
public class TravelCondition {

    //映射数据初始化
    public static final Map<Integer, TravelCondition> MAP_PEREXPEND = new HashMap<>();  //人均消费
    public static final Map<Integer, TravelCondition> MAP_DAY = new HashMap<>();  //旅游天数

    static{
        MAP_PEREXPEND.put(1, new TravelCondition(1, 999));
        MAP_PEREXPEND.put(2, new TravelCondition(1000, 6000));
        MAP_PEREXPEND.put(3, new TravelCondition(6001, 20000));
        MAP_PEREXPEND.put(4, new TravelCondition(20001, Integer.MAX_VALUE));

        MAP_DAY.put(1, new TravelCondition(0, 3));
        MAP_DAY.put(2, new TravelCondition(4, 7));
        MAP_DAY.put(3, new TravelCondition(8, 14));
        MAP_DAY.put(4, new TravelCondition(15, Integer.MAX_VALUE));

    }
    private int min;
    private int max;

    public TravelCondition(int min, int max){
        this.min = min;
        this.max = max;
    }

}
