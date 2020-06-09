package cn.wolfcode.luowowo.listener;

import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.redis.IStrategyStatisRedisService;
import cn.wolfcode.luowowo.redis.vo.StrategyStatisVO;
import cn.wolfcode.luowowo.service.IStrategyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * redis数据初始化监听器
 */
//@Component
public class RedisDataInitListener implements ApplicationListener<ContextRefreshedEvent>{
   @Autowired
   private IStrategyService strategyService;
   @Autowired
   private IStrategyStatisRedisService strategyStatisRedisService;

    //spring容器初始化完之后马上调用
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //1: 获取所有攻略
        List<Strategy> listAll = strategyService.listAll();
        for(Strategy strategy : listAll){
            //存在一个问题:如果系统再重启,之前页面统计(改动)之后的数据会被覆盖掉
            //如果vo对象已经被初始化过,需要跳过
            if (strategyStatisRedisService.isStrategyStatisVoExists(strategy.getId())){
                continue;
            }
            //2: 遍历攻略,然后创建vo对象,并设置值
            StrategyStatisVO vo = new StrategyStatisVO();
            BeanUtils.copyProperties(strategy,vo);
            vo.setStrategyId(strategy.getId());
            //3: 将vo对象添加到redis中
            strategyStatisRedisService.setStrategyStatisVO(vo);
        }
    }
}
