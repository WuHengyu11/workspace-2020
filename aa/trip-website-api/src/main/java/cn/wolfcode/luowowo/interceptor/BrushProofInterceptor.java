package cn.wolfcode.luowowo.interceptor;

import cn.wolfcode.luowowo.redis.service.ISecurityRedisService;
import cn.wolfcode.luowowo.redis.util.RedisKeys;
import cn.wolfcode.luowowo.util.JsonResult;
import cn.wolfcode.luowowo.util.RequestUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 防刷拦截
 */
public class BrushProofInterceptor implements HandlerInterceptor {
    @Autowired
    private ISecurityRedisService securityRedisService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //请求接口：url
        //http://localhost:8080/xxx/xxx
        String url = request.getRequestURI().substring(1);
        //请求ip:
        String ip = RequestUtil.getIPAddress();
        //是否超频率
        String key = RedisKeys.BRUSH_PROOF.join(url, ip);
        if(!securityRedisService.isAllowBrush(key)){
            //表示已经超频率：
            //比如某个ip频繁出现超频现象， 一些额外的处理黑名单
            response.setContentType("text/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(JsonResult.error(500, "请勿频繁访问","谢谢咯")));
            return false;
        }
        return true;
    }
}
