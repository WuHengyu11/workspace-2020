package cn.wolfcode.luowowo.listener;

import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.redis.service.IStrategyStatisVORedisService;
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
 * spring容器一准备好之后马上执行数据初始化逻辑
 * 将mongodb数据(vo)加载到redis中
 */
//@Component
public class RedisDataInitListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private IStrategyService strategyService;
    @Autowired
    private IStrategyStatisVORedisService strategyStatisVORedisService;
    //spring容器创建完成（aop ioc di）之后马上执行
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("--------------------vo-start-------------------------");
        //1: 将攻略所有数据查询出来（点赞数， 回复数，收藏数，分享数，评论数）
        List<Strategy> list =  strategyService.list();
        //2:构建成vo对象存到redis中
        for (Strategy strategy : list) {
            //思考：如果vo对象已经存在redis中了， 是否再初始化呢？
            //如果vo对象已经存在redis了， 必须跳过
            //原因：因为已经初始化vo， 前端进行收藏， 点赞，评论等操作， 会修改vo里面属性值， 如果
            //再初始化， 那么数据vo数据会被覆盖， 原先前端操作数据会丢失。
            //如果vo存在redis中， 跳过这个vo初始化
            if(strategyStatisVORedisService.isVoExsits(strategy.getId())){
                continue;
            }

            StrategyStatisVO vo = new StrategyStatisVO();
            BeanUtils.copyProperties(strategy, vo);
            vo.setStrategyId(strategy.getId());  //id设置

            //添加到redis中
            strategyStatisVORedisService.setStrategyStatisVo(vo);
        }
        System.out.println("--------------------vo-end-------------------------");
    }
}
