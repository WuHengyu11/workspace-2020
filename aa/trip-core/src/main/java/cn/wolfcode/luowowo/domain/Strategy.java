package cn.wolfcode.luowowo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 攻略
 */
@Setter
@Getter
@ToString
@Document("strategy")
public class Strategy extends BaseDomain {
    public static final int  ABROAD_NO = 0;  //国内
    public static final int  ABROAD_YES = 1;  //国外

    public static final int STATE_NORMAL = 0;  //带发布
    public static final int STATE_PUBLISH = 1; //发布

    private String destId;  //关联的目的地
    private String destName;

    private String themeId; //关联主题
    private String themeName;

    private String catalogId;  //关联的分类
    private String catalogName;

    private String title;  //标题

    private String subTitle; //副标题

    private String summary;  //内容摘要

    private String coverUrl;  //封面

    private Date createTime;  //创建时间

    private int isabroad = ABROAD_NO;  //是否是国外

    private int viewnum;  //点击数

    private int replynum;  //攻略评论数

    private int favornum; //收藏数

    private int sharenum; //分享数

    private int thumbsupnum; //点赞个数

    private int state = STATE_NORMAL;  //状态

    private String content; //攻略内容

    public String getStateDisplay(){
        return state == STATE_PUBLISH ? "发布" :"待发布";
    }
}