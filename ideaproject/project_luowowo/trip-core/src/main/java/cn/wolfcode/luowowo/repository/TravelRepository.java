package cn.wolfcode.luowowo.repository;

import cn.wolfcode.luowowo.domain.Region;
import cn.wolfcode.luowowo.domain.Travel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 游记持久化接口
 */
@Repository
public interface TravelRepository extends MongoRepository<Travel,String> {
    /**
     * 指定目的地下游记前3
     * @param destId
     * @param pageable
     * @return
     */
    List<Travel> findByDestId(String destId, Pageable pageable);

    Travel getContentById(String id);
}
