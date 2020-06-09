package cn.wolfcode.luowowo.repository;

import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.domain.StrategyTheme;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 攻略主题持久化接口
 */
@Repository
public interface StrategyThemeRepository extends MongoRepository<StrategyTheme,String> {

}
