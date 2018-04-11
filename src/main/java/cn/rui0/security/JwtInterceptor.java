package cn.rui0.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ruilin on 2018/4/10.
 */
public class JwtInterceptor extends HandlerInterceptorAdapter {

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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException("invalid Authorization header");
        }
        //取得token
        String token = authHeader.substring(7);
        try {
            jwtUtil.checkToken(token);
            return true;
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
}