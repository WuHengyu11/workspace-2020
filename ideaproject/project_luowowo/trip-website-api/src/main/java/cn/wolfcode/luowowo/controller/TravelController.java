package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.annotation.RequireLogin;
import cn.wolfcode.luowowo.annotation.UserParam;
import cn.wolfcode.luowowo.domain.Travel;
import cn.wolfcode.luowowo.domain.TravelComment;
import cn.wolfcode.luowowo.domain.UserInfo;
import cn.wolfcode.luowowo.query.TravelQuery;
import cn.wolfcode.luowowo.redis.IUserInfoRedisService;
import cn.wolfcode.luowowo.service.IDestinationService;
import cn.wolfcode.luowowo.service.ITravelCommentService;
import cn.wolfcode.luowowo.service.ITravelService;
import cn.wolfcode.luowowo.util.JsonResult;
import cn.wolfcode.luowowo.util.UMEditorUploader;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("travels")
public class TravelController {
    @Autowired
    private ITravelService travelService;
    @Autowired
    private IDestinationService destinationService;
    @Autowired
    private IUserInfoRedisService userInfoRedisService;
    @Autowired
    private ITravelCommentService travelCommentService;

    @GetMapping("/query")
    public Object query(TravelQuery qo){
        Page<Travel> query = travelService.query(qo);
        return JsonResult.success(query);
    }

    @GetMapping("/input")
    public Object input(String id){
        Map<String,Object> map = new HashMap<>();
        map.put("tv",travelService.get(id));
        map.put("dests",destinationService.listAll());
        return JsonResult.success(map);
    }

    //上传图片
    @RequestMapping("/contentImage")
    @ResponseBody
    public String uploadUEImage(MultipartFile upfile, HttpServletRequest request) throws Exception{
        UMEditorUploader up = new UMEditorUploader(request);
        String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
        up.setAllowFiles(fileType);
        up.setMaxSize(10000); //单位KB
        up.upload(upfile);

        String callback = request.getParameter("callback");
        String result = "{\"name\":\""+ up.getFileName() +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize()
                +", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \""+ up.getUrl() +"\"}";
        result = result.replaceAll( "\\\\", "\\\\" );
        if(callback == null ){
            return result ;
        }else{
            return "<script>"+ callback +"(" + result + ")</script>";
        }
    }

    @RequireLogin  //用户必须登录
    @PostMapping("/saveOrUpdate")
    public Object saveOrUpdate(Travel travel,HttpServletRequest request){
        //用户对象id--获取当前登录用户
        //通过请求对象获取页面传回来的token,通过token去redis中获取
        String token = request.getHeader("token");
        UserInfo userByToken = userInfoRedisService.getUserByToken(token);
        travel.setUserId(userByToken.getId());
        //travel.setUserId(to);
        travelService.saveOrUpdate(travel);
        return JsonResult.success(travel.getId());
    }

    @GetMapping("/detail")
    public Object detail(String id){
        return JsonResult.success(travelService.get(id));
    }

    @RequireLogin  //用户必须登录
    @PostMapping("/commentAdd")
    public Object commentAdd(TravelComment comment, @UserParam UserInfo userInfo){
        //用户对象id--获取当前登录用户
        //通过请求对象获取页面传回来的token,通过token去redis中获取
        //String token = request.getHeader("token");
        //UserInfo userByToken = userInfoRedisService.getUserByToken(token);
        BeanUtils.copyProperties(userInfo,comment);
        comment.setUserId(userInfo.getId());
        comment.setCreateTime(new Date());
       travelCommentService.save(comment);
        return JsonResult.success();
    }

    //游记评论的列表
    @GetMapping("/comments")
    public Object comments(String travelId){
        return JsonResult.success(travelCommentService.queryByTravelId(travelId));
    }
}
