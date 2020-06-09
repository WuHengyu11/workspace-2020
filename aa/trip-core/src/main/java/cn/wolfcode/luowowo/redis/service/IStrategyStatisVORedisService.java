package cn.wolfcode.luowowo.redis.service;

import cn.wolfcode.luowowo.redis.vo.StrategyStatisVO;

import java.util.List;

/**
 * 攻略统计对象在redis操作接口
 */
public interface IStrategyStatisVORedisService {

    /**
     * 阅读数+1
     * @param sid
     * @param i 个数
     */
    void viewnumIncrease(String sid, int i);

    /**
     * 获取vo对象
     * @param sid
     * @return
     */
    StrategyStatisVO getStrategyStatisVo(String sid);

    /**
     * 设置vo对象
     * @param vo
     */
    void setStrategyStatisVo(StrategyStatisVO vo);

    /**
     * 攻略评论+1
     * @param sid
     * @param i
     */
    void replynumIncrease(String sid, int i);

    /**
     * 攻略收藏
     * @param sid
     * @param id
     * true 收藏成功， false表示取消收藏
     */
    boolean favor(String sid, String id);

    /**
     * 查询用户收藏攻略id
     * @param uid
     * @return
     */
    List<String> getSids(String uid);

    /**
     * 攻略点赞
     * @param sid
     * @param uid
     * @return
     */
    boolean thumbup(String sid, String uid);

    /**
     * 判断vo是否存，
     * @param sid
     * @return  true：存在， false 不存在
     */
    boolean isVoExsits(String sid);

    /**
     * 通过表达式获取所有vo对象集合
     * @param pattern
     * @return
     */
    List<StrategyStatisVO> listStrategyVoByPattern(String pattern);
}
