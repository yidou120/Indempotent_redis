package com.edou.indempotent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @Description //此注解标识需要有幂等性的接口方法上,拦截器会对有此注解的方法实现幂等性操作
 * @Date 2020/4/11 13:03
 *@return
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoIdempotent {
}
