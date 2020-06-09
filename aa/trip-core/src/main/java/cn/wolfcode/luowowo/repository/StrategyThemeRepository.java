package cn.wolfcode.luowowo.repository;


import cn.wolfcode.luowowo.domain.StrategyTheme;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 攻略分类持久层接口
 */
@Repository
public interface StrategyThemeRepository extends MongoRepository<StrategyTheme, String>{


}
