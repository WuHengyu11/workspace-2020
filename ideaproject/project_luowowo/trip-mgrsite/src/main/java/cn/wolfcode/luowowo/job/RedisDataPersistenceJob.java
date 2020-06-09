package cn.wolfcode.luowowo.job;

import cn.wolfcode.luowowo.redis.IStrategyStatisRedisService;
import cn.wolfcode.luowowo.redis.util.RedisKeys;
import cn.wolfcode.luowowo.redis.vo.StrategyStatisVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * redis定时任务类用于数据持久化
 */
@Component
public class RedisDataPersistenceJob {
    @Autowired
    private IStrategyStatisRedisService strategyStatisRedisService;
    //@Scheduled定时任务设置标签
    //cron设置 定时任务计划,执行规则
    @Scheduled(cron = "0/20 * * * * ? ")
    public void doWork(){
        System.out.println("------------vo对象持久化--begin----------------");
        //1: 从redis中查找需要持久化得vo
        List<StrategyStatisVO> vos = strategyStatisRedisService.queryStrategyStatisVos(RedisKeys.STRATEGY_STATIS_VO.join("*"));
        //2: 遍历vo对象,统一update 到MongoDB中
        System.out.println("------------vo对象持久化--end----------------");
    }
}
