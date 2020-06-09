package cn.wolfcode.luowowo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 攻略评论
 */
@Setter
@Getter
@Document("strategy_comment")
@ToString
public class StrategyComment extends BaseDomain {
    private String strategyId;  //攻略(明细)id
    private String strategyTitle; //游记标题
    private String userId;    //用户id
    private String nickname;  //用户名
    private String city;
    private int level;
    private String headImgUrl;     //头像
    private Date createTime;    //创建时间
    private String content;      //评论内容
    private int thumbupnum;     //点赞数
    private List<String> thumbuplist = new ArrayList<>();
}