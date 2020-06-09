package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.annotation.RequireLogin;
import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.domain.StrategyComment;
import cn.wolfcode.luowowo.domain.UserInfo;
import cn.wolfcode.luowowo.query.StrategyCommentQuery;
import cn.wolfcode.luowowo.query.StrategyQuery;
import cn.wolfcode.luowowo.redis.IUserInfoRedisService;
import cn.wolfcode.luowowo.service.IStrategyCommentService;
import cn.wolfcode.luowowo.service.IStrategyService;
import cn.wolfcode.luowowo.service.IStrategyThemeService;
import cn.wolfcode.luowowo.util.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("strategies")
public class StrategyController {
    @Autowired
    private IStrategyService strategyService;
    @Autowired
    private IStrategyThemeService strategyThemeService;
    @Autowired
    private IUserInfoRedisService userInfoRedisService;
    @Autowired
    private IStrategyCommentService strategyCommentService;

    @GetMapping("/detail")
    public Object detail(String id){
        return JsonResult.success(strategyService.get(id));
    }

    @GetMapping("/query")
        public Object query(StrategyQuery qo){
        return JsonResult.success(strategyService.query(qo));
    }

    @GetMapping("/themes")
    public Object themes(){
        return JsonResult.success(strategyThemeService.listAll());
    }

    //攻略评论添加
    @RequireLogin
    @PostMapping("/addComment")
    public Object addComment(StrategyComment strategyComment, HttpServletRequest request){
        //用户相关信息
        String token = request.getHeader("token");
        UserInfo user = userInfoRedisService.getUserByToken(token);
        //设置评论中用户相关属性
        //功能是将源对象中属性复制到目标对象属性中
        //采用的是同名原则 --javabean的内省方式
        BeanUtils.copyProperties(user,strategyComment);//javabean属性拷贝:参数1:源对象,参数2:目标对象
        strategyComment.setUserId(user.getId());
        strategyComment.setCreateTime(new Date());
        strategyCommentService.save(strategyComment);
        return JsonResult.success();
    }

    //攻略评论的分页
    @GetMapping("/comments")
    public Object comments(StrategyCommentQuery qo){
        Page page = strategyCommentService.query(qo);
        return JsonResult.success(page);
    }

    //评论点赞
    @RequireLogin
    @PostMapping("/commentThumb")
    //cid:攻略id
    public Object commentThumb(String cid,HttpServletRequest request){
        String token = request.getHeader("token");
        UserInfo user = userInfoRedisService.getUserByToken(token);
        //点赞
        strategyCommentService.commentThumb(cid,user.getId());
        return JsonResult.success();
    }
}
