package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.domain.StrategyComment;
import cn.wolfcode.luowowo.query.StrategyCommentQuery;
import cn.wolfcode.luowowo.query.StrategyQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 攻略评论服务接口
 */
public interface IStrategyCommentService {

    /**
     * 查单个
     * @param id
     * @return
     */
    StrategyComment get(String id);
    /**
     * 分页
     * @param qo
     * @return
     */
    Page<StrategyComment> query(StrategyCommentQuery qo);

    /**
     * 评论添加
     * @param comment
     */
    void addComment(StrategyComment comment);

    /**
     * 点赞
     * @param cid
     * @param uid
     */
    void commentThumb(String cid, String uid);
}
