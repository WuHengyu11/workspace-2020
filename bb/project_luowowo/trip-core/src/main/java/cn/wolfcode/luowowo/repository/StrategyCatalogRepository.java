package cn.wolfcode.luowowo.repository;

import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.domain.StrategyCatalog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 攻略分类持久化接口
 */
@Repository
public interface StrategyCatalogRepository extends MongoRepository<StrategyCatalog,String> {
    /**
     * 通过目的地id查询分类
     * @param destId
     * @return
     */
    List<StrategyCatalog> findByDestId(String destId);
}
