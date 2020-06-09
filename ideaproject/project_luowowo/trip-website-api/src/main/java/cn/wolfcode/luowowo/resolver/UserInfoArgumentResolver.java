package cn.wolfcode.luowowo.resolver;


import cn.wolfcode.luowowo.annotation.UserParam;
import cn.wolfcode.luowowo.domain.UserInfo;
import cn.wolfcode.luowowo.redis.IUserInfoRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 能将请求映射方法形式参数中UserInfo对象转换成当前登录用户对象
 */
public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private IUserInfoRedisService userInfoRedisService;
    //表示当前的参数解析器,支持哪种参数类型解析
    //此处支持解析UserInfo这种参数类型
    //如果该方法返回true, 表示 UserInfoArgumentResolver 支持对UserInfo类型解析
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType() == UserInfo.class && methodParameter.hasParameterAnnotation(UserParam.class);
    }
    //执行解析逻辑: 请求映射方法形式参数中UserInfo对象转换成当前登录用户对象
    //当上面supportsParameter方法返回true, 才执行这个resolveArgument
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        //请求头--token ---userInof
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String token = request.getHeader("token");
        return userInfoRedisService.getUserByToken(token);
    }
}
