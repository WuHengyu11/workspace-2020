package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.StrategyTheme;
import cn.wolfcode.luowowo.query.StrategyThemeQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 攻略主题的服务
 */
public interface IStrategyThemeService {
    /**
     * 攻略主题的添加
     * @param strategyTheme
     */
    void save(StrategyTheme strategyTheme);

    /**
     * 攻略主题的删除
     * @param id
     */
    void delete(String id);

    /**
     * 攻略主题的更新
     * @param strategyTheme
     */
    void update(StrategyTheme strategyTheme);

    /**
     * 攻略主题的获取(单个)
     */
    StrategyTheme get(String id);

    /**
     * 攻略主题的获取(多个)
     * @return
     */
    List<StrategyTheme> listAll();

    /**
     * 攻略主题的分页查询
     */
    Page query(StrategyThemeQuery qo);

    /**
     * 攻略的增加或更新
     * @param strategyTheme
     */
    void saveOrUpdate(StrategyTheme strategyTheme);
}
