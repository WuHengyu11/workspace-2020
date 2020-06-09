package cn.wolfcode.web.controller;

import cn.wolfcode.domain.City;
import cn.wolfcode.domain.Province;
import cn.wolfcode.qo.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Controller
public class JSONController {
    @RequestMapping("/getProvinces")
    @ResponseBody
    public List<Province>getProvinces(){
        return Province.getAllProvince();
    }
    @RequestMapping("/getCities")
    @ResponseBody
    public List<City>getProvinces(Long ProvinceId){
        return City.getCityByProvinceId(ProvinceId);
    }

    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(String username,String passwrod) {
        JsonResult jsonResult = new JsonResult();
        if ("lony".equals(username) && "123".equals(passwrod)) {
            jsonResult.setSuccess(true);
            jsonResult.setMsg("登录成功");

        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("用户名或密码错误");
        }
        return jsonResult;
    }
    @RequestMapping("/exisUsername.do")
    @ResponseBody
    public JsonResult existsUsername(String username){
      JsonResult jsonResult = new JsonResult();
        if("lony".equals(username)){
            jsonResult.setSuccess(false);
            jsonResult.setMsg("用户已经被注册");

        }else{
            jsonResult.setSuccess(true);
            jsonResult.setMsg("恭喜您可以注册了");
        }
        return jsonResult;
    }
    //定义一个方法响应JSON{"success":true,"msg":”当前时间”}
    //servlet api
    //之前使用 respsonse 对象响应HTML数据
    @RequestMapping("/json1")
    public void json1(HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();

        String now = new Date().toLocaleString();
        String content = "{\"success\":true,\"msg\":\""+now+"\"}";
        writer.write(content);
    }
    @RequestMapping("/json2")
    @ResponseBody
    public JsonResult json2()  {
        JsonResult jsonResult= new JsonResult();
        jsonResult.setMsg(new Date().toLocaleString());
        jsonResult.setSuccess(true);
        return jsonResult;
    }
}
