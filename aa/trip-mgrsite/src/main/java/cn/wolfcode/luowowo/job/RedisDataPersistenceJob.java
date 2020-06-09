package cn.wolfcode.luowowo.job;

import cn.wolfcode.luowowo.redis.service.IStrategyStatisVORedisService;
import cn.wolfcode.luowowo.redis.util.RedisKeys;
import cn.wolfcode.luowowo.redis.vo.StrategyStatisVO;
import cn.wolfcode.luowowo.service.IStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 将redis中数据进行持久化
 */
//@Component
public class RedisDataPersistenceJob {

    @Autowired
    private IStrategyStatisVORedisService strategyStatisVORedisService;

    @Autowired
    private IStrategyService strategyService;


    /**
     * Cron表达式是一个字符串，字符串以5或6个空格隔开，分为6或7个域，每一个域代表一个含义，
     * Cron有如下两种语法格式：
     * 1： Seconds Minutes Hours DayofMonth Month DayofWeek Year
     *      秒       分      时      几号      月     周几     年
     * 2： Seconds Minutes Hours DayofMonth Month DayofWeek     [spring]
     *      秒       分      时      几号      月     周几
     *
     *      0        0       2      1        *       ?       *   表示在每月的1日的凌晨2点调整任务
     *      0       15      10      ?        *     MON-FRI       表示周一到周五每天上午10:15执行作业
     */
    //定时任务标签
    //cron指定任务计划：什么时候执行doWork方法，周期是多少
    //定时任务时间间隔：没有标准，redis数据量决定，如果数据量较大， 时间周期适当缩短。
    @Scheduled(cron = "0/20 * * * * ? ")
    public void doWork(){
        System.out.println("----------------vo持久化-strat------------------------");
        //1: 获取所有的攻略vo数据
        //strategy_statis_vo:*
        String pattern = RedisKeys.STRATEGY_STATIS_VO.join("*");
        List<StrategyStatisVO> vos =  strategyStatisVORedisService
                .listStrategyVoByPattern(pattern);

        //2：更新vo对应的攻略，改动攻略统计字段(viewnum, replynum, .....)
        for (StrategyStatisVO vo : vos) {
            strategyService.updateStatisVo(vo);
        }
        System.out.println("----------------vo持久化-end------------------------");
    }
}
