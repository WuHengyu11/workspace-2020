package cn.wolfcode.luowowo.repository;

import cn.wolfcode.luowowo.domain.TravelComment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 游记评论持久化接口
 */
@Repository
public interface TravelCommentRepository extends MongoRepository<TravelComment,String> {

    /**
     * 通过游记id查询评论
     * @param travelId
     * @return
     */
    List<TravelComment> findByTravelId(String travelId);
}
