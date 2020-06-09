package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.query.StrategyQuery;
import cn.wolfcode.luowowo.redis.vo.StrategyStatisVO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 攻略服务接口
 */
public interface IStrategyService {

    /**
     * 查单个
     * @param id
     * @return
     */
    Strategy get(String id);

    /**
     * 分页
     * @param qo
     * @return
     */
    Page<Strategy> query(StrategyQuery qo);

    /**
     * 添加与更新
     * @param strategy
     */
    void saveOrUpdate(Strategy strategy);


    /**
     * 删除
     * @param id
     */
    void delete(String id);

    /**
     * 通过攻略分类的id查询攻略文章列表
     * @param id
     * @return
     */
    List<Strategy> getStrategyByCatalogId(String id);

    /**
     * 指定目的地下攻略的点击量前3
     * @param destId
     * @return
     */
    List<Strategy> getViewnumTop3(String destId);

    /**
     * 查询所有的攻略集合
     * @return
     */
    List<Strategy> list();

    /**
     * 持久化攻略的表统计数据
     * @param vo
     */
    void updateStatisVo(StrategyStatisVO vo);

    /**
     * 目的地名词查询lis集合
     * @param keyword
     * @return
     */
    List<Strategy> findByDestName(String keyword);
}
