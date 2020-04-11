package com.edou.indempotent.interceptor;

import cn.hutool.json.JSONUtil;
import com.edou.indempotent.annotation.AutoIdempotent;
import com.edou.indempotent.exception.ServiceException;
import com.edou.indempotent.token.TokenService;
import com.edou.indempotent.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandle;
import java.util.Objects;

/**
 * @ClassName AutoIdempotentInterceptor
 * @Description 拦截器 拦截使用@AutoIdempotent
 * @Author 中森明菜
 * @Date 2020/4/11 13:44
 * @Version 1.0
 */
@Component
public class AutoIdempotentInterceptor implements HandlerInterceptor {

    @Autowired
    TokenService tokenService;

    //预处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Annotation methodAnnotation = handlerMethod.getMethodAnnotation(AutoIdempotent.class);
        if(Objects.nonNull(methodAnnotation)){
            return tokenService.checkToken(request);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
