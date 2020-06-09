package cn.wolfcode.luowowo.repository;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.Region;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 目的地持久化接口
 */
@Repository
public interface DestinationRepository extends MongoRepository<Destination,String> {
    /**
     * 通过ids查询集合
     * @param refIds
     * @return
     */
    List<Destination> findByIdIn(List<String> refIds);

    /**
     * 通过父名称查询目的地
     * @param parentName
     * @return
     */
    List<Destination> findByParentName(String 中国);

    /**
     * 通过父查询目的地
     * @param parentId
     * @param pageable
     * @return
     */
    List<Destination> findByParentId(String parentId, Pageable pageable);
}
