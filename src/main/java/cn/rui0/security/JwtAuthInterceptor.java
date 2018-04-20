package cn.rui0.security;

import cn.rui0.model.Userinfo;
import cn.rui0.security.annotation.IgnoreSecurity;
import cn.rui0.service.UserinfoService;
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
            throw new ServletException("invalid Authorization header");
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
//                throw new ServletException("token error");
                return false;
            }
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
//        return false;
    }
}