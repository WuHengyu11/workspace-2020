package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.Travel;
import cn.wolfcode.luowowo.domain.TravelComment;
import cn.wolfcode.luowowo.query.TravelCommentQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 游记评论服务接口
 */
public interface ITravelCommentService {

    /**
     * 查单个
     * @param id
     * @return
     */
    TravelComment get(String id);
    /**
     * 分页
     * @param qo
     * @return
     */
    Page<TravelComment> query(TravelCommentQuery qo);

    /**
     * 评论添加
     * @param comment
     */
    void addComment(TravelComment comment);


    List<TravelComment> queryByTravelId(String travelId);
}
