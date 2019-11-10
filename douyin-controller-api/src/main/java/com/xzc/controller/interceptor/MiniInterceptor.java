package com.xzc.controller.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaozhichao
 * @Date 2019/11/10
 * @Time 10:33
 * 拦截器,需要注册
 */
public class MiniInterceptor implements HandlerInterceptor {
    @Override
    //拦截请求在调用接口之前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(true){
            System.out.println("请求拦截....");
            return false;
        }
        //返回false，请求被拦截
        //返回true，请求ok
        return true;
    }

    @Override
    //调用接口之后，在图形渲染之前
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    //调用接口之后，渲染之后
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
