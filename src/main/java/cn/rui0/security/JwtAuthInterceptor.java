package cn.rui0.security;

import cn.rui0.model.Userinfo;
import cn.rui0.security.annotation.IgnoreSecurity;
import cn.rui0.service.UserinfoService;
import cn.rui0.util.ResponseData;
import cn.rui0.util.ReturnJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by Ruilin on 2018/4/10.
 */
@Component
public class JwtAuthInterceptor extends HandlerInterceptorAdapter {

    /**
     * 拦截器 从头部拿到认证信息并返回判断
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserinfoService userinfoService;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //设置允许跨域的配置
        // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的访问方法
        response.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS, DELETE, PATCH");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers","token,Origin, X-Requested-With, Content-Type, Accept");
//        response.setHeader("Content-Type","multipart/form-data");
//
        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");


        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String requestPath = request.getRequestURI();

        System.out.println("Method: " + method.getName() + ", IgnoreSecurity: " + method.isAnnotationPresent(IgnoreSecurity.class));
        System.out.println("requestPath: " + requestPath);
        //-----------

        if (method.isAnnotationPresent(IgnoreSecurity.class)) {
            return true;
        }


        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            throw new ServletException("invalid Authorization header");
//            ReturnJson.jsonReturn(response, ResponseData.unauthorized(),"invalid Authorization header",0);
        }


        //取得token
        String token = authHeader.substring(7);
        try {
            if (jwtUtil.checkToken(token)) {
                //返回用户对象
                Userinfo userinfo=userinfoService.findByUserId(jwtUtil.getUserId(token));
                request.setAttribute("currentUser", userinfo);
                return true;
            }else {
                //如果验证token失败，并且方法需要用户，返回401错误
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
//        return false;
    }
}