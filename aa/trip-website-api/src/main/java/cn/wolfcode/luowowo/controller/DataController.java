package cn.wolfcode.luowowo.controller;


import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.Strategy;
import cn.wolfcode.luowowo.domain.Travel;
import cn.wolfcode.luowowo.domain.UserInfo;
import cn.wolfcode.luowowo.search.domain.DestinationEs;
import cn.wolfcode.luowowo.search.domain.StrategyEs;
import cn.wolfcode.luowowo.search.domain.TravelEs;
import cn.wolfcode.luowowo.search.domain.UserInfoEs;
import cn.wolfcode.luowowo.search.service.IDestinationEsService;
import cn.wolfcode.luowowo.search.service.IStrategyEsService;
import cn.wolfcode.luowowo.search.service.ITravelEsService;
import cn.wolfcode.luowowo.search.service.IUserInfoEsService;
import cn.wolfcode.luowowo.service.IDestinationService;
import cn.wolfcode.luowowo.service.IStrategyService;
import cn.wolfcode.luowowo.service.ITravelService;
import cn.wolfcode.luowowo.service.IUserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@ApiIgnore
@RestController
public class DataController {

    //es服务

    @Autowired
    private IDestinationEsService  destinationEsService;
    @Autowired
    private IStrategyEsService strategyEsService;

    @Autowired
    private ITravelEsService travelEsService;

    @Autowired
    private IUserInfoEsService userInfoEsService;


    //mongodb服务
    @Autowired
    private IDestinationService destinationService;
    @Autowired
    private IStrategyService strategyService;
    @Autowired
    private ITravelService travelService;
    @Autowired
    private IUserInfoService userInfoService;



    @GetMapping("/dataInit")
    public Object dataInit(){

        //攻略
        List<Strategy> sts = strategyService.list();
        for (Strategy st : sts) {
            StrategyEs es = new StrategyEs();
            BeanUtils.copyProperties(st, es);
            strategyEsService.save(es);
        }
        //游记
        List<Travel> ts = travelService.list();
        for (Travel t : ts) {
            TravelEs es = new TravelEs();
            BeanUtils.copyProperties(t, es);
            travelEsService.save(es);
        }

        //用户
        List<UserInfo> uf = userInfoService.list();
        for (UserInfo u : uf) {
            UserInfoEs es = new UserInfoEs();
            BeanUtils.copyProperties(u, es);
            userInfoEsService.save(es);
        }


        //目的地
        List<Destination> dests = destinationService.list();
        for (Destination d : dests) {
            DestinationEs es = new DestinationEs();
            BeanUtils.copyProperties(d, es);
            destinationEsService.save(es);
        }

        return "ok";
    }













}
