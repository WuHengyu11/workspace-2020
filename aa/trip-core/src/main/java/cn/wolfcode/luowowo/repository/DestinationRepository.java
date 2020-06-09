package cn.wolfcode.luowowo.repository;


import cn.wolfcode.luowowo.domain.Destination;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 目的地持久层接口
 */
@Repository
public interface DestinationRepository extends MongoRepository<Destination, String>{

    /**
     * 通过id的集合查询目的的集合
     * @param refIds
     * @return
     */
    List<Destination> findByIdIn(List<String> refIds);


    /**
     * 通过父name查询
     * @param parentName
     * @return
     */
    List<Destination> findByParentName(String parentName);
    /**
     * 通过父id查询
     * @param parentId
     * @return
     */
    List<Destination> findByParentId(String parentId, Pageable of);

    Destination findByName(String keyword);
}
