package cn.wolfcode.luowowo.redis.impl;

import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.redis.IStrategyStatisRedisService;
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
public class StrategyStatisRedisServiceImpl implements IStrategyStatisRedisService {
    @Autowired
    private StringRedisTemplate template;
    @Autowired
    private IStrategyService strategyService;

    @Override
    public void increaseViewnum(String sid) {
        StrategyStatisVO vo = this.getStrategyStatisVO(sid);
        vo.setViewnum(vo.getViewnum() + 1);
        this.setStrategyStatisVO(vo);
    }

    @Override
    public StrategyStatisVO getStrategyStatisVO(String sid) {
        //1:判断是否存在
        String key = RedisKeys.STRATEGY_STATIS_VO.join(sid);
        //2:存在,直接返回vo对象
        if (template.hasKey(key)){
            String voStr = template.opsForValue().get(key);
            return JSON.parseObject(voStr,StrategyStatisVO.class);
        }
        //如果不存在,初始化,然后返回
        StrategyStatisVO strategyStatisVO = new StrategyStatisVO();
        Strategy strategy = strategyService.get(sid);
        BeanUtils.copyProperties(strategy,strategyStatisVO);
        strategyStatisVO.setStrategyId(strategy.getId());
        return strategyStatisVO;
    }

    @Override
    public void setStrategyStatisVO(StrategyStatisVO vo) {
        String key = RedisKeys.STRATEGY_STATIS_VO.join(vo.getStrategyId());
        template.opsForValue().set(key,JSON.toJSONString(vo));
    }

    @Override
    public void increaseRepynum(String sid) {
        StrategyStatisVO vo = this.getStrategyStatisVO(sid);
        vo.setReplynum(vo.getReplynum() + 1);
        this.setStrategyStatisVO(vo);
    }

    @Override
    public boolean favor(String sid, String uid) {
        boolean flag = true;
        //1:查看用户攻略id收藏集合是否存在,如果存在就获取,不存在就创建
        String listKey = RedisKeys.USER_STRATEGY_FAVOR.join(uid);
        List<String> sidList = new ArrayList<>();
        if (template.hasKey(listKey)){
            String listStr = template.opsForValue().get(listKey);
            //2:集合泛型
            sidList = JSON.parseArray(listStr,String.class);
        }
        StrategyStatisVO strategyStatisVO = this.getStrategyStatisVO(sid);
        //2:根据集合,判断当前传入的sid是否在集合中
        if (sidList.contains(sid)){
            //3:如果在表示取消收藏,vo对象中favornum属性 - 1,移除sid;
            strategyStatisVO.setFavornum(strategyStatisVO.getFavornum() - 1);
            sidList.remove(sid);
            flag = false;
        }else {
            //4:如果不在表示收藏,vo对象中favornum属性 + 1,添加sid
            strategyStatisVO.setFavornum(strategyStatisVO.getFavornum() + 1);
            sidList.add(sid);
        }
        //5:更新
        this.setStrategyStatisVO(strategyStatisVO);
        template.opsForValue().set(listKey,JSON.toJSONString(sidList));
        //return flag;
        return sidList.contains(uid);
    }

    @Override
    public List<String> getUserFavorList(String uid) {
        String listKey = RedisKeys.USER_STRATEGY_FAVOR.join(uid);
        List<String> sidList = new ArrayList<>();
        if (template.hasKey(listKey)){
            String listStr = template.opsForValue().get(listKey);
            //2:集合泛型
            sidList = JSON.parseArray(listStr,String.class);
        }
        return sidList;
    }

    @Override
    public boolean strategyThumbup(String sid, String uid) {
        //1: 判断标记的key是否存在
        //strategy_thumbup:sid:uid
        String key = RedisKeys.USER_STRATEGY_FAVOR.join(sid, uid);
        if (!template.hasKey(key)){
            //2: 如果key不存在,表示可以点赞,vo对象中点赞数+1,添加key设置有效时间
            StrategyStatisVO strategyStatisVO = this.getStrategyStatisVO(sid);
            strategyStatisVO.setThumbsupnum(strategyStatisVO.getThumbsupnum() + 1);
            this.setStrategyStatisVO(strategyStatisVO);
            //有效时间
            //几天最后一秒 +1 到 当前时间 间隔毫秒数
            Date now = new Date();
            Date endDate = DateUtil.getEndDate(now);
            long time = DateUtil.getDateBetween(now,endDate);;
            template.opsForValue().set(key,"1",time,TimeUnit.SECONDS);
            return true;
        }
        //3: 如果key存在,表示今天已经点赞过了,不做任何处理
        return false;
    }

    @Override
    public boolean isStrategyStatisVoExists(String sid) {
        String key = RedisKeys.STRATEGY_STATIS_VO.join(sid);
        return template.hasKey(key);
    }

    @Override
    public List<StrategyStatisVO> queryStrategyStatisVos(String pattern) {
        List<StrategyStatisVO> vos = new ArrayList<>();
        //所有vo对象在redis中key的集合
        Set<String> keys = template.keys(pattern);
        if (keys != null && keys.size() > 0){
            for (String voKey : keys){
                String voStr = template.opsForValue().get(voKey);
                vos.add(JSON.parseObject(voStr,StrategyStatisVO.class));
            }
        }
        return vos;
    }
}
