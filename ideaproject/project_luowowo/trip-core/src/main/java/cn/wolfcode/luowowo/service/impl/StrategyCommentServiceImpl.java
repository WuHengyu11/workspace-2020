package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.StrategyComment;
import cn.wolfcode.luowowo.query.StrategyCommentQuery;
import cn.wolfcode.luowowo.repository.StrategyCommentRepository;
import cn.wolfcode.luowowo.service.IDestinationService;
import cn.wolfcode.luowowo.service.IStrategyCommentService;
import cn.wolfcode.luowowo.util.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class StrategyCommentServiceImpl implements IStrategyCommentService {
    @Autowired
    private StrategyCommentRepository strategyCommentRepository;
    @Autowired
    private MongoTemplate template;
    @Autowired
    private IDestinationService destinationService;

    @Override
    public void save(StrategyComment strategyComment) {
        strategyComment.setId(null);
        strategyCommentRepository.save(strategyComment);
    }

    @Override
    public void delete(String id) {
        strategyCommentRepository.deleteById(id);
    }

    @Override
    public void update(StrategyComment strategyComment) {
        strategyCommentRepository.save(strategyComment);
    }

    @Override
    public StrategyComment get(String id) {
        Optional<StrategyComment> strategyComment = strategyCommentRepository.findById(id);
        if (strategyComment.isPresent()){
            return strategyComment.get();
        }
        return null;
    }

    @Override
    public List<StrategyComment> listAll() {
        return strategyCommentRepository.findAll();
    }

    @Override
    public Page query(StrategyCommentQuery qo) {
        //分页条件对象
        Query query = new Query();
       if (StringUtils.hasLength(qo.getStrategyId())){
           query.addCriteria(Criteria.where("strategyId").is(qo.getStrategyId()));
       }
        Pageable pageable = PageRequest.of(qo.getCurrentPage() - 1,qo.getPageSize());
        //每页显示记录
        return DBHelper.query(template,StrategyComment.class,query,pageable);
    }

    @Override
    public void commentThumb(String cid, String uid) {
        //获取攻略评论中点赞用户id集合
        StrategyComment comment = this.get(cid);//获取攻略
        List<String> uidList = comment.getThumbuplist();//用户id集合
        //判断当前用户id是否在集合中
        if (uidList.contains(uid)){
            //如果在表示当前请求是取消点赞,点赞数-1,将用户id移除
            comment.setThumbupnum(comment.getThumbupnum() - 1);
            uidList.remove(uid);
        }else{
            //如果不在表示当前请求是点赞操作,点赞数+1,将用户id添加到集合中
            comment.setThumbupnum(comment.getThumbupnum() + 1);
            uidList.add(uid);
        }
        //更新集合数据
        this.update(comment);
    }
}
