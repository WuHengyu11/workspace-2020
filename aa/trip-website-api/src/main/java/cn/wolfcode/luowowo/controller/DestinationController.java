package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.domain.StrategyCatalog;
import cn.wolfcode.luowowo.query.StrategyQuery;
import cn.wolfcode.luowowo.service.*;
import cn.wolfcode.luowowo.util.JsonResult;
import cn.wolfcode.luowowo.util.ParamMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("destinations")
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
        return JsonResult.success(regionService.queryHotRegion());
    }

    @GetMapping("/search")
    public Object search(String regionId){

        List<Destination> list =  destinationService.getDestByRegionIdForApi(regionId);
       return JsonResult.success(list);

    }


    @GetMapping("/detail")
    public Object detail(String id){
        return JsonResult.success(destinationService.get(id));
    }

    @GetMapping("/strategies/viewnumTop3")
    public Object strategyViewnumTop3(String destId){
        return JsonResult.success(strategyService.getViewnumTop3(destId));
    }



    @GetMapping("/travels/viewnumTop3")
    public Object travelViewnumTop3(String destId){
        return JsonResult.success(travelService.getViewnumTop3(destId));
    }


    @GetMapping("/catalogs")
    public Object catalogs(String destId){
        return JsonResult.success(strategyCatalogService.queryCatalogByDestId(destId));
    }


    @GetMapping("/toasts")
    public Object getToast(String destId){
        return JsonResult.success(destinationService.getToasts(destId));

    }



}
