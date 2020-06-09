package cn.wolfcode.luowowo.repository;


import cn.wolfcode.luowowo.domain.Strategy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 攻略持久层接口
 */
@Repository
public interface StrategyRepository extends MongoRepository<Strategy, String>{


    /**
     * 通过分类id查询攻略集合
     * @param catalogId
     * @return
     */
    List<Strategy> findByCatalogId(String catalogId);
    /**
     * 通过目的地id查询攻略前3
     * @param destId
     * @return
     */
    List<Strategy> findByDestId(String destId, Pageable of);


    /**
     * 通过目的地id查询
     * @param destName
     * @return
     */
    List<Strategy> findByDestName(String destName);
}
