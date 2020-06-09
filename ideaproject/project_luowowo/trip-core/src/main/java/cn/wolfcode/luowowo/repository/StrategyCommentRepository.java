package cn.wolfcode.luowowo.repository;

import cn.wolfcode.luowowo.domain.Region;
import cn.wolfcode.luowowo.domain.StrategyComment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论持久化接口
 */
@Repository
public interface StrategyCommentRepository extends MongoRepository<StrategyComment,String> {
}
