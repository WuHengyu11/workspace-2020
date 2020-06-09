package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.annotation.RequireLogin;
import cn.wolfcode.luowowo.annotation.UserParam;
import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.domain.StrategyComment;
import cn.wolfcode.luowowo.domain.UserInfo;
import cn.wolfcode.luowowo.query.StrategyCommentQuery;
import cn.wolfcode.luowowo.query.StrategyQuery;
import cn.wolfcode.luowowo.redis.IStrategyStatisRedisService;
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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
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
    @Autowired
    private IStrategyStatisRedisService strategyStatisRedisService;

    @GetMapping("/detail")
    public Object detail(String id){
        //操作redis把阅读数+1数据放到redis中
        strategyStatisRedisService.increaseViewnum(id);
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

        //评论数+1
        strategyStatisRedisService.increaseRepynum(strategyComment.getStrategyId());
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

    //自定义一个
    @GetMapping("/info")
    public Object info(UserInfo userInfo){
        //当前登录用户
        return JsonResult.success(userInfo);
    }

    @GetMapping("/update")
    public Object update(UserInfo userInfo){
        return JsonResult.success(userInfo);
    }

    @GetMapping("statisVo")
    public Object statisVo(String sid){
        return JsonResult.success(strategyStatisRedisService.getStrategyStatisVO(sid));
    }

    //攻略收藏
    @RequireLogin
    @PostMapping("/favor")
    public Object favor(String sid, @UserParam UserInfo userInfo){
        boolean ret = strategyStatisRedisService.favor(sid,userInfo.getId());
        return JsonResult.success(ret);
    }

    //攻略顶
    @RequireLogin
    @PostMapping("/strategyThumbup")
    public Object strategyThumbup(String sid, HttpServletRequest request){
        String token = request.getHeader("token");
        UserInfo userInfo = userInfoRedisService.getUserByToken(token);
        boolean ret = strategyStatisRedisService.strategyThumbup(sid,userInfo.getId());
        return JsonResult.success(ret);
    }
}
