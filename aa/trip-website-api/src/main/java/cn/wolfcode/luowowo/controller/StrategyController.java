package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.annotation.RequireLogin;
import cn.wolfcode.luowowo.annotation.UserParam;
import cn.wolfcode.luowowo.domain.*;
import cn.wolfcode.luowowo.query.StrategyCommentQuery;
import cn.wolfcode.luowowo.query.StrategyQuery;
import cn.wolfcode.luowowo.redis.service.IStrategyStatisVORedisService;
import cn.wolfcode.luowowo.service.*;
import cn.wolfcode.luowowo.util.JsonResult;
import cn.wolfcode.luowowo.util.ParamMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("strategies")
public class StrategyController {

    @Autowired
    private IStrategyCatalogService strategyCatalogService;

    @Autowired
    private IStrategyService strategyService;
    @Autowired
    private IStrategyThemeService strategyThemeService;

    @Autowired
    private IDestinationService destinationService;

    @Autowired
    private IStrategyCommentService strategyCommentService;

    @Autowired
    private IStrategyStatisVORedisService strategyStatisVORedisService;

    //攻略明细
    @GetMapping("/detail")
    public Object detail(String id){
        Strategy strategy = strategyService.get(id);
        //阅读数+1
        strategyStatisVORedisService.viewnumIncrease(id, 1);
        return JsonResult.success(strategy);
    }
    //攻略明细
    @GetMapping("/statisVo")
    public Object statisVo(String sid){
        return JsonResult.success(strategyStatisVORedisService.getStrategyStatisVo(sid));
    }

    //攻略主题
    @GetMapping("/themes")
    public Object ListAllThemes(){
       return JsonResult.success(strategyThemeService.list());
    }


    //攻略明细分页
    @GetMapping("/query")
    public Object query(StrategyQuery qo){
       return JsonResult.success(strategyService.query(qo));
    }


    @GetMapping("/comments")
    public Object comments(StrategyCommentQuery qo){
        qo.setPageSize(3);
        return JsonResult.success(strategyCommentService.query(qo));
    }




    @RequireLogin
    @PostMapping("/addComment")
    public Object addComment(StrategyComment comment, @UserParam UserInfo userInfo){

        //设置用户信息
        //同名属性才可以进行属性拷贝
        BeanUtils.copyProperties(userInfo, comment);
        comment.setUserId(userInfo.getId());  //用户id设置
        strategyCommentService.addComment(comment);
        //评论数+i
        strategyStatisVORedisService.replynumIncrease(comment.getStrategyId(), 1);

        return JsonResult.success();
    }


    //cid 攻略评论id
    //sid 攻略id
    @RequireLogin
    @PostMapping("/commentThumb")
    public Object commentThumb(String cid, String sid, @UserParam UserInfo userInfo){

        //点赞
        strategyCommentService.commentThumb(cid, userInfo.getId());
        return JsonResult.success();
    }



    //攻略收藏
    @RequireLogin
    @PostMapping("/favor")
    public Object favor(String sid, @UserParam UserInfo userInfo){

        //攻略收藏 : ret true 收藏成功， false表示取消收藏
        boolean ret = strategyStatisVORedisService.favor(sid, userInfo.getId());


        return JsonResult.success(ret);
    }
    //攻略点赞
    @RequireLogin
    @PostMapping("/strategyThumbup")
    public Object strategyThumbup(String sid, @UserParam UserInfo userInfo){

        //攻略点赞 : ret true 点赞成功， false表示今天已经点过
        boolean ret = strategyStatisVORedisService.thumbup(sid, userInfo.getId());


        return JsonResult.success(ret);
    }


}
