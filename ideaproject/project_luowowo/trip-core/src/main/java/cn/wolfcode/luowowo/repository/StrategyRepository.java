package cn.wolfcode.luowowo.repository;

import cn.wolfcode.luowowo.domain.Region;
import cn.wolfcode.luowowo.domain.Strategy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 攻略持久化接口
 */
@Repository
public interface StrategyRepository extends MongoRepository<Strategy,String> {

    /**
     * 通过攻略分类id查询集合
     * @param catalogId
     * @return
     */
    List<Strategy> findByCatalogId(String catalogId);

    /**
     * 通过指定id查询点击量前3的攻略
     * @param destId
     * @param pageable
     */
    List<Strategy> findByDestId(String destId, Pageable pageable);
}
