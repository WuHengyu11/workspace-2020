package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.StrategyTheme;
import cn.wolfcode.luowowo.query.StrategyThemeQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 攻略主题服务接口
 */
public interface IStrategyThemeService {

    /**
     * 查单个
     * @param id
     * @return
     */
    StrategyTheme get(String id);

    /**
     * 分页
     * @param qo
     * @return
     */
    Page<StrategyTheme> query(StrategyThemeQuery qo);

    /**
     * 添加与更新
     * @param strategyTheme
     */
    void saveOrUpdate(StrategyTheme strategyTheme);


    /**
     * 删除
     * @param id
     */
    void delete(String id);

    /**
     * 查询所有
     * @return
     */
    List<StrategyTheme> list();
}
