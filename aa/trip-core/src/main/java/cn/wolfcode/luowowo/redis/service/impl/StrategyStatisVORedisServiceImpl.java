package cn.wolfcode.luowowo.redis.service.impl;

import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.redis.service.IStrategyStatisVORedisService;
import cn.wolfcode.luowowo.redis.service.IStrategyStatisVORedisService;
import cn.wolfcode.luowowo.redis.util.RedisKeys;
import cn.wolfcode.luowowo.redis.vo.StrategyStatisVO;
import cn.wolfcode.luowowo.service.IStrategyService;
import cn.wolfcode.luowowo.util.DateUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Service
public class StrategyStatisVORedisServiceImpl implements IStrategyStatisVORedisService {



    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private IStrategyService strategyService;


    @Override
    public void replynumIncrease(String sid, int i) {
        //判断vo对象是否存在， 如果不存在， 先要初始化vo对象
        StrategyStatisVO vo = this.getStrategyStatisVo(sid);
        //然后在执行评论数 + i 操作
        vo.setReplynum(vo.getReplynum() + i);
        //同步更新redis中数据
        this.setStrategyStatisVo(vo);
    }

    @Override
    public void viewnumIncrease(String sid, int i) {
        //判断vo对象是否存在， 如果不存在， 先要初始化vo对象
        StrategyStatisVO vo = this.getStrategyStatisVo(sid);
        //然后在执行阅读数 + i 操作
        vo.setViewnum(vo.getViewnum() + i);
        //同步更新redis中数据
        this.setStrategyStatisVo(vo);
    }

    //将vo对象设置到redis中
    @Override
    public void setStrategyStatisVo(StrategyStatisVO vo) {
        String key = RedisKeys.STRATEGY_STATIS_VO.join(vo.getStrategyId());
        template.opsForValue().set(key, JSON.toJSONString(vo));
    }

    //从redis中获取数据
    @Override
    public StrategyStatisVO getStrategyStatisVo(String sid) {
        //判断vo对象是否存在， 如果不存在， 先要初始化vo对象
        //vo对象的key:  strategy_statis_vo:sid
        String key = RedisKeys.STRATEGY_STATIS_VO.join(sid);
        StrategyStatisVO vo = null;
        if (!template.hasKey(key)) {
            //不存在， 需要从数据库中查询并初始化
            Strategy strategy = strategyService.get(sid);
            vo = new StrategyStatisVO();
            BeanUtils.copyProperties(strategy,vo);
            //vo.setViewnum(strategy.getViewnum());
            //....
            vo.setStrategyId(sid);

            //需要将初始化数据设置redis中
            template.opsForValue().set(key, JSON.toJSONString(vo));
        }else{
            //存在
            String voStr = template.opsForValue().get(key);
            vo = JSON.parseObject(voStr, StrategyStatisVO.class);
        }

        return vo;
    }


    @Override
    public boolean favor(String sid, String uid) {
        //1:构建攻略id收藏集合list ://user_strategy_favor:uid
        String listKey = RedisKeys.USER_STRATEGY_FAVOR.join(uid);
        List<String> list = null;  //攻略id收藏集合list
        if(template.hasKey(listKey)){ //如果已经存在了，获取
            String listStr = template.opsForValue().get(listKey);
            //将字符串转换成list集合， 参数2：集合泛型
            list = JSON.parseArray(listStr, String.class);
        }else{
            //最初，攻略id收藏集合list根本不存在， 需要创建并初始化
            list = new ArrayList<>();
        }
        //2:判断攻略id是否在上面list中
        StrategyStatisVO vo = this.getStrategyStatisVo(sid);
        boolean flag = true;
        if(!list.contains(sid)){
            //3:如果攻略id不在list，收藏操作， favornum + 1 将sid加到list
            vo.setFavornum(vo.getFavornum() + 1);
            list.add(sid);
            flag  = true;
        }else{
            //4：如果攻略id在list， 取消操作， favornum -1， 将sid移除出list
            vo.setFavornum(vo.getFavornum() - 1);
            list.remove(sid);
            flag  = false;
        }
        //5:更新list, vo
        template.opsForValue().set(listKey, JSON.toJSONString(list));
        this.setStrategyStatisVo(vo);

        return flag/*list.contains(sid)*/;  //只要list中存在攻略id，表示收藏操作
    }


    @Override
    public List<String> getSids(String uid) {
        String listKey = RedisKeys.USER_STRATEGY_FAVOR.join(uid);
        List<String> list = null;  //攻略id收藏集合list
        if(template.hasKey(listKey)){ //如果已经存在了，获取
            String listStr = template.opsForValue().get(listKey);
            //将字符串转换成list集合， 参数2：集合泛型
            list = JSON.parseArray(listStr, String.class);
        }else{
            //最初，攻略id收藏集合list根本不存在， 需要创建并初始化
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public boolean thumbup(String sid, String uid) {

        //1：判断标记是否存在， 如果不存在则创建
        //strategy_thumbup:uid:sid
        String signKey = RedisKeys.STRATEGY_THUMBUP.join(uid, sid);

        if(!template.hasKey(signKey)){
            //2:如果不存在， 点赞数+ 1， 将标记添加到redis中， 设置有效时间，更新vo对象

            StrategyStatisVO vo = this.getStrategyStatisVo(sid);
            vo.setThumbsupnum(vo.getThumbsupnum() + 1);
            this.setStrategyStatisVo(vo);
            Date now = new Date();
            //今天最后一秒那个时间- now 到秒数
            Date endDate = DateUtil.getEndDate(now);  //今天最后一秒时间对象
            long time = DateUtil.getDateBetween(now, endDate);  //有效时间， 单位秒
            template.opsForValue().set(signKey, "1",time,TimeUnit.SECONDS);
            return true;
        }

        //3:如果存在， 表示已经点赞过了， 直接返回false
        return false;
    }

    @Override
    public boolean isVoExsits(String sid) {
        String key = RedisKeys.STRATEGY_STATIS_VO.join(sid);
        return template.hasKey(key);
    }

    @Override
    public List<StrategyStatisVO> listStrategyVoByPattern(String pattern) {

        //vo的key集合
        // keys strategy_statis_vo:*
        Set<String> keys = template.keys(pattern);

        List<StrategyStatisVO> list = new ArrayList<>();
        if(keys != null && keys.size() > 0){
            for (String voKey : keys) {
                String voStr = template.opsForValue().get(voKey);
                list.add(JSON.parseObject(voStr, StrategyStatisVO.class));
            }
        }
        return list;
    }
}
