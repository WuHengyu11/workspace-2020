package cn.wolfcode.luowowo.repository;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.Region;
import cn.wolfcode.luowowo.domain.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 区域持久化接口
 */
@Repository
public interface RegionRepository extends MongoRepository<Region,String> {
    /**
     * 查询热门区域按照序号正序排列
     * @param stateHot
     * @return
     */
    List<Region> findByIshotOrderBySequenceAsc(int stateHot);
}
