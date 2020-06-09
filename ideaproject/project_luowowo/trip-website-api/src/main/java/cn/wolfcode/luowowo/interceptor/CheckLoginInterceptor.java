package cn.wolfcode.luowowo.interceptor;

import cn.wolfcode.luowowo.domain.UserInfo;
import cn.wolfcode.luowowo.redis.IUserInfoRedisService;
import cn.wolfcode.luowowo.util.JsonResult;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录的拦截器
 */

public class CheckLoginInterceptor implements HandlerInterceptor {
    @Autowired
    private IUserInfoRedisService userInfoRedisService;
    /**
     * 实现拦截逻辑
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    /*
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //如果非controller中的请求映射方法,直接放行
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        //获取当前登录用户对象
        String token = request.getHeader("token");
        UserInfo user = userInfoRedisService.getUserByToken(token);
        if (user == null) {
            //没登录
            response.getWriter().write(JSON.toJSONString(JsonResult.noLogin()));
            return false;
        }
        //放行
        return true;
    }
        */
}
