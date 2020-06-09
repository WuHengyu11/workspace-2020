package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.TravelComment;

import java.util.List;

/**
 * 游记评论的服务
 */
public interface ITravelCommentService {
    /**
     * 游记评论的添加
     * @param travelComment
     */
    void save(TravelComment travelComment);

    /**
     * 游记评论的获取(单个)
     */
    TravelComment get(String id);

    /**
     * 游记评论的列表查询
     * @param travelId
     * @return
     */
    List<TravelComment> queryByTravelId(String travelId);
}
