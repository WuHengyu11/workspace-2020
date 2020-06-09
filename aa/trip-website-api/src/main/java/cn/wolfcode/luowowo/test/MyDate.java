package cn.wolfcode.luowowo.test;


import lombok.Getter;
import lombok.Setter;

/**
 * 枚举类特点
 *
 * 1: 构造器是私有
 * 2: 枚举类实例是固定,一定枚举类定义好之后, 实例个数就固定
 * 3: 剩下操作跟普通类一样
 *
 *
 */

public enum MyDate {
    day1("day1", 1L), day2("day2", 2L), day3("day3", 3L);
    @Setter
    @Getter
    private String prefix;
    @Setter
    @Getter
    private Long time;

    private MyDate(String prefix, Long time){
        this.prefix = prefix;
        this.time = time;
    }
}
class MyDate2 {
    public static final MyDate2 day1 = new MyDate2("day1", 1L);
    public static final MyDate2 day2 = new MyDate2("day2", 2L);
    public static final MyDate2 day3 = new MyDate2("day3", 3L);
    @Setter
    @Getter
    private String prefix;
    @Setter
    @Getter
    private Long time;

    private MyDate2(String prefix, Long time){
        this.prefix = prefix;
        this.time = time;
    }
}
