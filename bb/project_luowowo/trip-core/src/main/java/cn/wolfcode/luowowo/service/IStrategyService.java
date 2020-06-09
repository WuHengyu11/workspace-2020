package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.query.StrategyQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 攻略的服务
 */
public interface IStrategyService {
    /**
     * 攻略的添加
     * @param strategy
     */
    void save(Strategy strategy);

    /**
     * 攻略的删除
     * @param id
     */
    void delete(String id);

    /**
     * 攻略的更新
     * @param strategy
     */
    void update(Strategy strategy);

    /**
     * 攻略的获取(单个)
     */
    Strategy get(String id);

    /**
     * 攻略的获取(多个)
     * @return
     */
    List<Strategy> listAll();

    /**
     * 攻略的分页查询
     */
    Page query(StrategyQuery qo);

    /**
     * 攻略的增加或更新
     * @param strategy
     */
    void saveOrUpdate(Strategy strategy);

    /**
     *通过分类id查询该分类所有攻略集合
     * @param catalogId
     * @return
     */
    List<Strategy> queryByCatalogId(String catalogId);

    /**
     * 指定目的地下攻略点击量前三
     * @param destId
     * @return
     */
    List<Strategy> queryViewnumTop3(String destId);
}
