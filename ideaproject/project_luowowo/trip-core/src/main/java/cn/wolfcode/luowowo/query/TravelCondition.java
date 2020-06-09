package cn.wolfcode.luowowo.query;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 游记范围条件
 */
@Getter
public class TravelCondition {
    //查询范围数据初始化
    //旅游天数范围映射map
    public static final Map<Integer,TravelCondition> DAY_MAP = new HashMap<>();
    //旅游人均消费范围映射map
    public static final Map<Integer,TravelCondition> PEREXPEND_MAP = new HashMap<>();

    static{
        DAY_MAP.put(1,new TravelCondition(0,3));
        DAY_MAP.put(2,new TravelCondition(4,7));
        DAY_MAP.put(3,new TravelCondition(8,14));
        DAY_MAP.put(4,new TravelCondition(15,Integer.MAX_VALUE));

        PEREXPEND_MAP.put(1, new TravelCondition(1, 999));
        PEREXPEND_MAP.put(2, new TravelCondition(1000, 6000));
        PEREXPEND_MAP.put(3, new TravelCondition(6001, 200000));
        PEREXPEND_MAP.put(4, new TravelCondition(200001,Integer.MAX_VALUE));
    }

    private int min; //最小值
    private int max; //最大值

    public TravelCondition(int min,int max){
        this.max = max;
        this.min = min;
    }
}
