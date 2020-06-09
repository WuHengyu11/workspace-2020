package cn.wolfcode.luowowo.interceptor;

import cn.wolfcode.luowowo.annotation.RequireLogin;
import cn.wolfcode.luowowo.domain.UserInfo;
import cn.wolfcode.luowowo.redis.service.IUserInfoRedisService;
import cn.wolfcode.luowowo.util.JsonResult;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截
 */
public class CheckLoginInterceptor implements HandlerInterceptor{
    @Autowired
    private IUserInfoRedisService userInfoRedisService;

    //登录拦截
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception  {
        //1: 判断访问请求对应的方法头顶是否贴了: @RequireLogin
        //handler 请求映射方法对象
        //拦截器可以拦截静态请求(DefaultResouceeHandler..), 动态请求(HandlerMethod)
        //因为OPTION请求方法, 进来拦截器, springmvc做了额外处理
        // 他的请求对象并不是HandlerMethod, 而是其他对象.
        //spirngmvc启动时候, 会将所有controller中请求映射方法加载, 并解析
        //解析请求方法路径, 解析请求方法, 封装成一个对象: HandlerMethod
        //将解析玩的请求方法, 放置一个类似map存储结构:
        /**
         *     key                  value
         *    映射路径         请求方法封装对象
         *  currentUser       HandlerMethod
         *  get               HandlerMethod
         */
        //currentUser
        //每次请求进入拦截器时, 拦截器先获取当前请求url 假设是: currentUser
        //通过这个url, 去map中获取对应的HandlerMethod, 注入到preHandle
        if(handler instanceof HandlerMethod){
            //动态请求对象
            //currentUser  hm  --> LoginController.currentUser
            //get    hm  --> LoginController.get
            HandlerMethod hm  = (HandlerMethod) handler;
            //2:如果贴了这个标签, 获取token, 查询用户信息, 根据用户对象是否存在, 判断是否登录
            if(hm.hasMethodAnnotation(RequireLogin.class)){
                String token = request.getHeader("token");
                UserInfo user = userInfoRedisService.getUserByToken(token);
                if(user == null){
                    response.getWriter().write(JSON.toJSONString(JsonResult.noLogin()));
                    return false;
                }
            }
        }
        //3:如果没贴, 直接放行
        return true;
    }
}
