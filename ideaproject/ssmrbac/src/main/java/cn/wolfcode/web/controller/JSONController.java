package cn.wolfcode.web.controller;

import cn.wolfcode.domain.City;
import cn.wolfcode.domain.Province;
import cn.wolfcode.qo.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class JSONController {

    @RequestMapping("/existusername")
    @ResponseBody
    public JsonResult existUsername (String username){
        JsonResult jsonResult =  new JsonResult();
        if ("admin".equals(username)){
            jsonResult.setMsg("成功");
            jsonResult.setSuccess(true);
        }else {
            jsonResult.setMsg("失败");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }
    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login (String username,String password){
        JsonResult jsonResult =  new JsonResult();
        if ("admin".equals(username) && "112".equals(password)){
            jsonResult.setMsg("成功");
            jsonResult.setSuccess(true);
        }else {
            jsonResult.setMsg("失败");
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }
    @RequestMapping("/getProvinces")
    @ResponseBody
    public List<Province> getProvinces(){
        return Province.getAllProvince();
    }

    @RequestMapping("/getCities")
    @ResponseBody
    public List<City>getProvinces(Long pid){
        return City.getCityByProvinceId(pid);
    }
}
