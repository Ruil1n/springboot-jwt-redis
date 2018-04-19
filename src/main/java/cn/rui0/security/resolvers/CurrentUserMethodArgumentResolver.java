package cn.rui0.security.resolvers;

import cn.rui0.model.Userinfo;
import cn.rui0.security.annotation.CurrentUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * Created by Ruilin on 2018/4/19.
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果参数类型是Userinfo并且有CurrentUser注解则支持
        //return parameter.getParameterType().isAssignableFrom(Userinfo.class) && parameter.hasParameterAnnotation(CurrentUser.class);
        if (parameter.getParameterType().isAssignableFrom(Userinfo.class) &&
                parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Userinfo userInfo = (Userinfo) webRequest.getAttribute("currentUser", RequestAttributes.SCOPE_REQUEST);
        if (userInfo != null) {
            return userInfo;
        }
        throw new MissingServletRequestPartException("currentUser");
    }
}
