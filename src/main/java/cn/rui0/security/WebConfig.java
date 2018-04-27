package cn.rui0.security;

import cn.rui0.security.resolvers.CurrentUserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

//需要注意，拦截器中引用了 UserService 所以在注册时需要使用 @Bean 的形式以告诉 Spring 注入
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;

    //关键，将拦截器作为bean写入配置中
    @Bean
    public JwtAuthInterceptor getSecurityInterceptor() {
        return new JwtAuthInterceptor();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //允许全部请求跨域
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("PUT", "DELETE","GET","POST")
//                .allowedHeaders("*")
//                .allowCredentials(false).maxAge(3600);
        registry.addMapping("/**").allowedOrigins("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        // 注册拦截器
//        InterceptorRegistration ir = registry.addInterceptor(getSecurityInterceptor());
        // 配置拦截的路径
//        ir.addPathPatterns("**");
        // 配置不拦截的路径
//        ir.excludePathPatterns("**/swagger-ui.html");
        // 还可以在这里注册其它的拦截器
//        System.out.println("aaaa");
//        registry.addInterceptor(new JwtAuthInterceptor()).addPathPatterns("/api/**");
//        registry.addInterceptor(new JwtAuthInterceptor()).addPathPatterns("/main/**");
        registry.addInterceptor(getSecurityInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(getSecurityInterceptor()).excludePathPatterns("/user/login");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver);
        super.addArgumentResolvers(argumentResolvers);
    }

    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver() {
        return new CurrentUserMethodArgumentResolver();
    }
}
