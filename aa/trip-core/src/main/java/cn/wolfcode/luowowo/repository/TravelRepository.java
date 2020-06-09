package cn.wolfcode.luowowo.repository;


import cn.wolfcode.luowowo.domain.Travel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 游记持久层接口
 */
@Repository
public interface TravelRepository extends MongoRepository<Travel, String>{

    /**
     * 指定目的地下, 游记点击量前3
     * @param destId
     * @return
     */
    List<Travel> findByDestId(String destId, Pageable of);
    /**
     * 指定目的地下, 游记
     * @param destName
     * @return
     */
    List<Travel> findByDestName(String destName);
}
