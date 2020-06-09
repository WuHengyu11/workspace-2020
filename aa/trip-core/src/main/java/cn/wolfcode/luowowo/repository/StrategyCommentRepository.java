package cn.wolfcode.luowowo.repository;


import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.domain.StrategyComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 攻略评论持久层接口
 */
@Repository
public interface StrategyCommentRepository
        extends MongoRepository<StrategyComment, String>{

}
