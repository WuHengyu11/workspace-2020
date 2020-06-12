package cn.zhang.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;

import javax.print.attribute.standard.RequestingUserName;
import javax.servlet.http.HttpServletRequest;

@Component
public class AuthZuulPilter extends ZuulFilter {

    /**
     * 过滤器类型
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * true, 表示需要过滤 执行run()方法
     * false, 表示不需要过滤器 直接调用下一个过滤器 如果没有下一个过滤器 直接调用远程服务
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String requestURI = request.getRequestURI();
        System.out.println("请求的路径===>" + requestURI);
        if(requestURI.indexOf("/order")>-1){
            return true;
        }

        return false;
    }

    /**
     * 拦截之后需要处理的业务
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("拦截请求");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token) || !token.equals("134")){
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            currentContext.setSendZuulResponse(false);
        }

        return null;
    }
}
