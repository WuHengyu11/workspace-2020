package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.TravelComment;
import cn.wolfcode.luowowo.repository.TravelCommentRepository;
import cn.wolfcode.luowowo.service.ITravelCommentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TravelCommentServiceImpl implements ITravelCommentService {
    @Autowired
    private TravelCommentRepository travelCommentRepository;
    
    @Override
    public void save(TravelComment travelComment) {
        travelComment.setId(null);
        travelComment.setCreateTime(new Date());
        travelCommentRepository.save(travelComment);
        //处理引用评论
        String refId = travelComment.getRefComment().getId();
        if (StringUtils.hasLength(refId)){
            //表示该评论为评论的评论
            TravelComment ref = this.get(refId);
            travelComment.setRefComment(ref);
        }else {
            travelComment.setRefComment(null);
        }
//        if (travelComment.getType() == TravelComment.TRAVLE_COMMENT_TYPE){
//
//        }
    }


    @Override
    public TravelComment get(String id) {
        Optional<TravelComment> travelComment = travelCommentRepository.findById(id);
        if (travelComment.isPresent()){
            return travelComment.get();
        }
        return null;
    }

    @Override
    public List<TravelComment> queryByTravelId(String travelId) {
        return travelCommentRepository.findByTravelId(travelId);
    }
}
