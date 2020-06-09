package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.domain.Banner;
import cn.wolfcode.luowowo.domain.Travel;
import cn.wolfcode.luowowo.query.TravelQuery;
import cn.wolfcode.luowowo.service.IBannerService;
import cn.wolfcode.luowowo.service.ITravelService;
import cn.wolfcode.luowowo.util.JsonResult;
import cn.wolfcode.luowowo.util.ParamMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private IBannerService bannerService;

    @Autowired
    private ITravelService travelService;

    @GetMapping("/banners/travel")
    public Object travelBanners(){
        //banners 游记， 前5
        List<Banner> banners = bannerService.queryBanner(Banner.TYPE_TRAVEL);
        return JsonResult.success(banners);
    }

    @GetMapping("/banners/strategy")
    public Object strategyBanners(){
        //攻略推荐
        return JsonResult.success(bannerService.queryBanner(Banner.TYPE_STRATEGY).get(0));
    }
}
