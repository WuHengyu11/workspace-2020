package cn.wolfcode.luowowo.redis;

import cn.wolfcode.luowowo.redis.vo.StrategyStatisVO;

import java.util.List;

/**
 * 攻略统计接口
 */
public interface IStrategyStatisRedisService {
    /**
     * 攻略阅读数+1
     * @param sid
     */
    void increaseViewnum(String sid);

    /**
     * 获取或初始化VO对象
     * @param sid
     * @return
     */
    StrategyStatisVO getStrategyStatisVO(String sid);

    /**
     * 更新vo对象
     * @param vo
     * @return
     */
    void setStrategyStatisVO(StrategyStatisVO vo);

    /**
     * 攻略阅读数+1
     * @param sid
     */
    void increaseRepynum(String sid);

    /**
     * 攻略收藏
     * @param sid
     * @param uid
     * @retrun true:收藏 false:取消收藏
     */
    boolean favor(String sid, String uid);

    /**
     * 用户收藏攻略id
     * @param uid
     * @return
     */
    List<String> getUserFavorList(String uid);

    /**
     * 顶操作
     * @param sid
     * @param uid
     * @return
     */
    boolean strategyThumbup(String sid, String uid);

    /**
     *
     * @param sid
     * @return
     */
    boolean isStrategyStatisVoExists(String sid);

    /**
     * 通过pattern获取满足规则的vo对象
     * @param pattern
     * @return
     */
    List<StrategyStatisVO> queryStrategyStatisVos(String pattern);
}
