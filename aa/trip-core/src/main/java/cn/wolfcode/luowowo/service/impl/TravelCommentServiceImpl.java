package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.TravelComment;
import cn.wolfcode.luowowo.query.TravelCommentQuery;
import cn.wolfcode.luowowo.repository.TravelCommentRepository;
import cn.wolfcode.luowowo.service.ITravelCommentService;
import cn.wolfcode.luowowo.util.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional  暂时先别管事务
public class TravelCommentServiceImpl implements ITravelCommentService {


    @Autowired
    private TravelCommentRepository repository;
    @Autowired
    private MongoTemplate template;

    public TravelComment get(String id) {
        Optional<TravelComment> optional = repository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    //page对象里面包括: 总页数 每页显示条数......
    public Page<TravelComment> query(TravelCommentQuery qo) {
        Query query = new Query();

        if(StringUtils.hasLength(qo.getTravelId())){
            query.addCriteria(Criteria.where("travelId").is(qo.getTravelId()));
        }

        return DBHelper.query(template, TravelComment.class, query, qo.getPageable());
    }


    @Override
    public void addComment(TravelComment comment) {

        //时间
        comment.setCreateTime(new Date());
        comment.setId(null);

        //评论的评论
        String refId = comment.getRefComment().getId();
        if (StringUtils.hasLength(refId)) {
            //设置引入评论
            TravelComment refComment = this.get(refId);
            comment.setRefComment(refComment);
        }
        repository.save(comment);

    }

    @Override
    public List<TravelComment> queryByTravelId(String travelId) {

        return repository.findByTravelIdOrderByCreateTimeDesc(travelId);
    }
}
