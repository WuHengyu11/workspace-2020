package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.*;
import cn.wolfcode.luowowo.query.StrategyCommentQuery;
import cn.wolfcode.luowowo.query.StrategyQuery;
import cn.wolfcode.luowowo.repository.StrategyCommentRepository;
import cn.wolfcode.luowowo.repository.StrategyRepository;
import cn.wolfcode.luowowo.service.*;
import cn.wolfcode.luowowo.util.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class StrategyCommentServiceImpl implements IStrategyCommentService {


    @Autowired
    private StrategyCommentRepository repository;
    @Autowired
    private MongoTemplate template;

    public StrategyComment get(String id) {
        Optional<StrategyComment> optional = repository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    //page对象里面包括: 总页数 每页显示条数......
    public Page<StrategyComment> query(StrategyCommentQuery qo) {
        Query query = new Query();
        if(StringUtils.hasLength(qo.getStrategyId())){
            query.addCriteria(Criteria.where("strategyId").is(qo.getStrategyId()));
        }
        return DBHelper.query(template, StrategyComment.class, query, qo.getPageable());
    }


    @Override
    public void addComment(StrategyComment comment) {

        //时间
        comment.setCreateTime(new Date());
        comment.setId(null);

        repository.save(comment);

    }

    @Override
    public void commentThumb(String cid, String uid) {

        //1:判断是点赞还是取消点赞
        //获取用户id集合
        StrategyComment comment = this.get(cid);
        List<String> userList = comment.getThumbuplist();

        if(!userList.contains(uid)){
            //2:如果是点赞操作， 点赞数+1 用户id集合加uid
            comment.setThumbupnum(comment.getThumbupnum() + 1);
            userList.add(uid);
        }else{
            //3:如果是取消潜在， 点数-1， 用户id集合移除uid
            comment.setThumbupnum(comment.getThumbupnum() - 1);
            userList.remove(uid);
        }
        //4:更新
        repository.save(comment);
    }
}
