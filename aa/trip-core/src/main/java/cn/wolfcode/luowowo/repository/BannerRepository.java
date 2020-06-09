package cn.wolfcode.luowowo.repository;


import cn.wolfcode.luowowo.domain.Banner;
import cn.wolfcode.luowowo.domain.Region;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * banner持久层接口
 */
@Repository
public interface BannerRepository extends MongoRepository<Banner, String>{


    /**
     * 查询banner
     * @param type
     * @param state
     * @return
     */
    //List<Banner> findByTypeAndStateOrderBySequenceAsc(int type, int state);
    /**
     * 查询banner
     * @param type
     * @param state
     * @return
     */
    List<Banner> findByTypeAndState(int type, int state, Pageable of);
}
