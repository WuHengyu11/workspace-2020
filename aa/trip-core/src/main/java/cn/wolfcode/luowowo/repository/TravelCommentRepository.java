package cn.wolfcode.luowowo.repository;


import cn.wolfcode.luowowo.domain.StrategyComment;
import cn.wolfcode.luowowo.domain.TravelComment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 游记评论持久层接口
 */
@Repository
public interface TravelCommentRepository
        extends MongoRepository<TravelComment, String>{

    List<TravelComment> findByTravelIdOrderByCreateTimeDesc(String travelId);
}
