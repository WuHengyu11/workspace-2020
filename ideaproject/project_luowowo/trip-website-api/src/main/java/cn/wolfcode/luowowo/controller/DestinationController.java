package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.annotation.RequireLogin;
import cn.wolfcode.luowowo.domain.UserInfo;
import cn.wolfcode.luowowo.redis.IUserInfoRedisService;
import cn.wolfcode.luowowo.service.*;
import cn.wolfcode.luowowo.util.JsonResult;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/destinations")
public class DestinationController {
    @Autowired
    private IRegionService regionService;
    @Autowired
    private IDestinationService destinationService;
    @Autowired
    private IStrategyCatalogService strategyCatalogService;
    @Autowired
    private IStrategyService strategyService;
    @Autowired
    private ITravelService travelService;

   @GetMapping("/hotRegion")
    public Object hotRegion(){
       return JsonResult.success(regionService.queryHotRegions());
   }

    @GetMapping("/search")
    public Object search(String regionId){
        return JsonResult.success(destinationService.queryByRegionId(regionId));
    }

    @GetMapping("/toasts")
    public Object toasts(String destId){
        return JsonResult.success(destinationService.getToasts(destId));
    }

    @GetMapping("/catalogs")
    public Object catalogs(String destId){
        return JsonResult.success(strategyCatalogService.queryCatalogByDestId(destId));
    }
    @GetMapping("/strategies/viewnumTop3")
    public Object stsviewnumTop3(String destId){
        return JsonResult.success(strategyService.queryViewnumTop3(destId));
    }

    @GetMapping("/detail")
    public Object detail(){
       return JsonResult.success(destinationService.listAll());
    }

    @GetMapping("/travels/viewnumTop3")
    public Object tvviewnumTop3(String destId){
        return JsonResult.success(travelService.queryViewnumTop3(destId));
    }
}
