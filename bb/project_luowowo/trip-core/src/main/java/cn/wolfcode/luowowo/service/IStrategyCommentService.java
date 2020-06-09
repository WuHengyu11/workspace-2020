package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.StrategyComment;
import cn.wolfcode.luowowo.query.StrategyCommentQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 评论的服务
 */
public interface IStrategyCommentService {
    /**
     * 评论的添加
     * @param strategyComment
     */
    void save(StrategyComment strategyComment);

    /**
     * 评论的删除
     * @param id
     */
    void delete(String id);

    /**
     * 评论的更新
     * @param strategyComment
     */
    void update(StrategyComment strategyComment);

    /**
     * 评论的获取(单个)
     */
    StrategyComment get(String id);

    /**
     * 评论的获取(多个)
     * @return
     */
    List<StrategyComment> listAll();

    /**
     * 评论的分页查询
     */
    Page query(StrategyCommentQuery qo);

    /**
     * 点赞
     * @param cid 攻略id
     * @param uid 用户id
     */
    void commentThumb(String cid, String uid);
}
