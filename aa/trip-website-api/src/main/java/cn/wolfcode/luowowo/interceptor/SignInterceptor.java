package cn.wolfcode.luowowo.interceptor;

import cn.wolfcode.luowowo.util.JsonResult;
import cn.wolfcode.luowowo.util.Md5Utils;
import com.alibaba.fastjson.JSON;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 签名拦截(防篡改)
 */
public class SignInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return  true;
        }

       // aa: 1
      //  bb: 2
      //  cc: 3
       // sign: 10DBF1B8602134BDCA51A64AA59B665F

        /**
         * Map<String, String[]> map = request.getParameterMap();
         *  {
         *      aa:["1"],
         *      bb:["2"]
         *      cc:["3"]
         *      sign:["10DBF1B8602134BDCA51A64AA59B665F"]
         *  }
         *  {
         *      aa:"1",
         *      bb:"2"
         *      cc:"3"
         *  }
         */
        //签名验证
        Map<String, String[]> map = request.getParameterMap();
        Set<String> keys = map.keySet();
        Map<String, Object> param = new HashMap<>();
        for (String s : map.keySet()) {
            if("sign".equalsIgnoreCase(s)){
                continue;
            }
            param.put(s, arrayToString(map.get(s)));
        }
        String signatures = Md5Utils.signatures(param);  //获取服务端签名 sign_server
        String sign = request.getParameter("sign");  //sign_client
        if(sign == null || !sign.equalsIgnoreCase(signatures)){
            response.setContentType("text/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(new JsonResult(501,"签名校验失败","不好意思咯")));
            return false;
        }
        return true;
    }


    private String arrayToString(String [] array){

        StringBuilder sb = new StringBuilder(10);
        for (String s : array) {
            sb.append(s);
        }
        return sb.toString();
    }
}
