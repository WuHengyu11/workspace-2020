package cn.wolfcode.luowowo.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 游记
 */
@Setter
@Getter
@Document("travel")
public class Travel extends BaseDomain {

    public static final int STATE_NORMAL = 0;  //草稿
    public static final int STATE_WAITING = 1;  //待发布(审核中)
    public static final int STATE_RELEASE = 2;  //发布
    public static final int STATE_REJECT = 3;  //拒绝

    public static final int ISPUBLIC_NO = 0;
    public static final int ISPUBLIC_YES = 1;


    private String destId;  //目的地

    private String destName;  //目的地
    @Transient
    private Destination dest;  //目的地


    private String userId;  //作者id

    @Transient
    private UserInfo author;  //作者

    private String title;  //标题

    private String coverUrl; //封面

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date travelTime; //旅游时间

    private int perExpend; //人均消费

    private int day;  //旅游天数

    private int person;  //和谁旅游

    private Date createTime; //创建时间

    private Date releaseTime; //发布时间

    private Date lastUpdateTime; //最新更新时间内

    private int isPublic=ISPUBLIC_NO; //是否发布

    private int viewnum;  //点击/阅读数

    private int replynum; //回复数

    private int favornum;//收藏数

    private int sharenum;//分享数

    private int thumbsupnum;//点赞数

    private int state = STATE_NORMAL;//游记状态

    private String summary;//概要

    private String content;

    public String getStateDisplay(){
        if (state ==STATE_NORMAL ){
            return "编辑中";
        }else if(state ==STATE_WAITING ){
            return "待发布";
        }else if(state ==STATE_RELEASE ){
            return "已发布";
        }else if(state ==STATE_REJECT ){
            return "已拒绝";
        }
        return "";
    }

    public String getPersonDisplay(){
       if (person == 1){
           return "一个人";
       }else if (person == 2){
           return "情侣/夫妻";
       }else if (person == 3){
           return "带孩子";
       }else if (person == 4){
           return "家庭出游";
       }else if (person == 5){
           return "和朋友";
       }else if (person == 6){
           return "和同学";
       }else if (person == 7){
           return "其它";
       }
       return "其它";
    }
}