package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.annotation.RequireLogin;
import cn.wolfcode.luowowo.annotation.UserParam;
import cn.wolfcode.luowowo.domain.*;
import cn.wolfcode.luowowo.query.TravelCommentQuery;
import cn.wolfcode.luowowo.query.TravelQuery;
import cn.wolfcode.luowowo.redis.service.IUserInfoRedisService;
import cn.wolfcode.luowowo.service.IDestinationService;
import cn.wolfcode.luowowo.service.IStrategyService;
import cn.wolfcode.luowowo.service.ITravelCommentService;
import cn.wolfcode.luowowo.service.ITravelService;
import cn.wolfcode.luowowo.util.JsonResult;
import cn.wolfcode.luowowo.util.ParamMap;
import cn.wolfcode.luowowo.util.UMEditorUploader;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    private IStrategyService strategyService;

    @Autowired
    private ITravelCommentService travelCommentService;

    @GetMapping("/query")
    public Object query(TravelQuery qo){
        Page<Travel> page = travelService.query(qo);
        return JsonResult.success(page);
    }

    @GetMapping("/input")
    public Object input(String id){
        ParamMap map = new ParamMap();
        //tv
        if(StringUtils.hasLength(id)){
            Travel travel = travelService.get(id);
            map.put("tv", travel);
        }
        //dests
        List<Destination> dests = destinationService.list();
        map.put("dests", dests);
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


    @GetMapping("/detail")
    public Object detail(String id){
        Travel travel = travelService.get(id);
        return JsonResult.success(travel);
    }


    @RequireLogin   //需要进行等检查
    @PostMapping("/saveOrUpdate")
    public Object saveOrUpdate(Travel travel, HttpServletRequest request){
        //2:当前登录用户信息
        String token = request.getHeader("token");
        UserInfo user = userInfoRedisService.getUserByToken(token);
        if(user != null){
            travel.setUserId(user.getId());
        }
        //最新游记id
        String id = travelService.saveOrUpdate(travel);
        return JsonResult.success(id);
    }

    //问题: 每次进行获取当前登录用户信息,都需要使用request 获取token, 然后再通过token获取用户信息,
    //代码实现麻烦

    //思考: 是否可以通过参数注入的方式实现呢?
    //答案是yes, 需要使用springmvc 参数解析器知识点, 以前用户参数解析器: 文件上传参数解析器
    // springmvc 没有提供当前登录用户参数解析器, 需要我们自己定义.

    //自定义参数解析器, 在请求方法参数中自动注入当前登录用.

    //获取当前登录用户信息
    @GetMapping("/info")
    public Object info(@UserParam UserInfo userInfo){
        //userInfo  就是当前登录用户对象
        return JsonResult.success(userInfo);
    }

    //如果说需要收集页面传过来用户信息时, 此时也需要注入用户对象
    //存在问题了: 此时使用自定义用户解析器, 还是用springmvc默认参数解析器?
    //问题核心: 解析的参数存在区分对待了, 联想到 标签区分
    //解决方案: 对解析参数贴@UserParam 进行区分解析.

    @GetMapping("/userUpdate")
    public Object update(UserInfo userInfo){
        //userInfo  就是当前登录用户对象
        return JsonResult.success(userInfo);
    }


    @RequireLogin
    @PostMapping("/commentAdd")
    public Object commentAdd(TravelComment comment, @UserParam UserInfo userInfo){

        //用户游记评论
        BeanUtils.copyProperties(userInfo, comment);
        comment.setUserId(userInfo.getId());
        travelCommentService.addComment(comment);

        return JsonResult.success();
    }

    @GetMapping("/comments")
    public Object comments(String travelId){
        return JsonResult.success(travelCommentService.queryByTravelId(travelId));
    }




















}
