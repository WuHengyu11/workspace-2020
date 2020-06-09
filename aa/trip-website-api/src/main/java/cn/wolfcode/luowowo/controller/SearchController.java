package cn.wolfcode.luowowo.controller;


import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.domain.Travel;
import cn.wolfcode.luowowo.domain.UserInfo;
import cn.wolfcode.luowowo.search.domain.DestinationEs;
import cn.wolfcode.luowowo.search.domain.StrategyEs;
import cn.wolfcode.luowowo.search.domain.TravelEs;
import cn.wolfcode.luowowo.search.domain.UserInfoEs;
import cn.wolfcode.luowowo.search.query.SearchQueryObject;
import cn.wolfcode.luowowo.search.service.*;
import cn.wolfcode.luowowo.search.vo.SearchResult;
import cn.wolfcode.luowowo.service.IDestinationService;
import cn.wolfcode.luowowo.service.IStrategyService;
import cn.wolfcode.luowowo.service.ITravelService;
import cn.wolfcode.luowowo.service.IUserInfoService;
import cn.wolfcode.luowowo.util.JsonResult;
import cn.wolfcode.luowowo.util.ParamMap;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private IDestinationEsService destinationEsService;
    @Autowired
    private IDestinationService destinationService;
    @Autowired
    private IStrategyEsService strategyEsService;
    @Autowired
    private IStrategyService strategyService;
    @Autowired
    private ITravelEsService travelEsService;
    @Autowired
    private ITravelService travelService;

    @Autowired
    private IUserInfoEsService userInfoEsService;
    @Autowired
    private IUserInfoService userInfoService;


    @Autowired
    private ISearchService searchService;


    @GetMapping("/q")
    public Object search(SearchQueryObject qo) throws Exception {
        //%E5%B9%BF%E5%B7%9E -->广州
        //%E5%B9%BF%E5%B7%9E  浏览器get请求对中文url编码
        //通过解析出成中文
        qo.setKeyword(URLDecoder.decode(qo.getKeyword(), "UTF-8"));

        switch (qo.getType()){
            case  SearchQueryObject.TYPE_DEST :
                //目的地
                return searchDest(qo);
            case  SearchQueryObject.TYPE_STRATEGY :
                //攻略
                return searchStrategy(qo);
            case  SearchQueryObject.TYPE_TRAVEL :
                //游记
                return searchTravel(qo);
            case  SearchQueryObject.TYPE_USER :
                //用户
                return searchUser(qo);
            default:
                //全部
                return searchAll(qo);
        }
    }


    //查询目的地
    private Object searchDest(SearchQueryObject qo) {
        //查询当前输入keyword是否不是一个目的地

        //因为es查询数据，导入头像没了， 使用es使用mongodb去查询
        Destination dest = destinationService.getByName(qo.getKeyword());

        SearchResult result = new SearchResult();
        if(dest != null){
            //如果是，查询该目的地下所有攻略，游记，用户
            //result  -- total strategys  travels users
            //List<StrategyEs> list =  strategyEsService.findByDestName(qo.getKeyword());
            List<Strategy> sts = strategyService.findByDestName(qo.getKeyword());
            List<Travel> ts = travelService.findByDestName(qo.getKeyword());
            List<UserInfo> us = userInfoService.findByCity(qo.getKeyword());
            long total = sts.size() + ts.size() + us.size();

            result.setTotal(total);
            result.setStrategys(sts);
            result.setTravels(ts);
            result.setUsers(us);
        }
        //dest
        //qo
        return JsonResult.success(new ParamMap()
                .put("qo", qo)
                .put("result", result)
                .put("dest", dest));
    }





    //全部
    private Object searchAll(SearchQueryObject qo) {
        SearchResult result = new SearchResult();
        Page<UserInfo> us = searchService.searchWithHighlight(UserInfoEs.INDEX_NAME, UserInfoEs.TYPE_NAME,
                UserInfo.class,qo,"info",  "city"
        );
        Page<Travel> ts = searchService.searchWithHighlight(TravelEs.INDEX_NAME,  TravelEs.TYPE_NAME,
                Travel.class,qo, "title",  "summary"
        );
        Page<Strategy> sts = searchService.searchWithHighlight(StrategyEs.INDEX_NAME, StrategyEs.TYPE_NAME,
                Strategy.class,qo, "title", "subTitle", "summary"
        );





        Page<Destination> ds = searchService.searchWithHighlight(
                DestinationEs.INDEX_NAME, DestinationEs.TYPE_NAME,    Destination.class,
                qo, "name", "info"
        );
        result.setUsers(us.getContent());
        result.setTravels(ts.getContent());
        result.setStrategys(sts.getContent());
        result.setDests(ds.getContent());

        result.setTotal(us.getTotalElements()
                + ts.getTotalElements() + sts.getTotalElements() + ds.getTotalElements());

        return JsonResult.success(new ParamMap().put("result",result ).put("qo",qo));
    }

    //用户
    private Object searchUser(SearchQueryObject qo) {
        Page<UserInfo> page = searchService.searchWithHighlight(
                UserInfoEs.INDEX_NAME,  //索引
                UserInfoEs.TYPE_NAME,   //类型
                UserInfo.class,         //攻略对象类型
                qo,                     //分页数据
                "info",  "city"  //搜索条件列
        );
        return JsonResult.success(new ParamMap().put("page", page).put("qo", qo));
    }

    //游记
    private Object searchTravel(SearchQueryObject qo) {


        Page<Travel> page = searchService.searchWithHighlight(
                TravelEs.INDEX_NAME,  //索引
                TravelEs.TYPE_NAME,   //类型
                Travel.class,         //攻略对象类型
                qo,                     //分页数据
                "title",  "summary"  //搜索条件列
        );


        return JsonResult.success(new ParamMap().put("page", page).put("qo", qo));
    }
    //攻略
    private Object searchStrategy(SearchQueryObject qo) {

        //全文搜索
        /**
         * query:{
         *     multi_match{
         *         query:"keyword"
         *         fields:["title", "intro"]
         *     }
         * }
         */
        //高亮显示
        Page<Strategy> page = searchService.searchWithHighlight(
                StrategyEs.INDEX_NAME,  //索引
                StrategyEs.TYPE_NAME,   //类型
                Strategy.class,         //攻略对象类型
                qo,                     //分页数据
                "title", "subTitle", "summary"  //搜索条件列
        );


        return JsonResult.success(new ParamMap().put("page", page).put("qo", qo));
    }



}
