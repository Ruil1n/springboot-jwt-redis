package cn.rui0.security.annotation;

import java.lang.annotation.*;

/**
 * Created by Ruilin on 2018/4/17.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreSecurity {

}