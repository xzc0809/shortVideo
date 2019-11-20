package com.xzc.controller.interceptro;

import com.xzc.common.Constants;
import com.xzc.common.EmptyUtils;
import com.xzc.common.RedisUtil;
import com.xzc.utils.JSONResult;
import com.xzc.utils.JsonUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author xiaozhichao
 * @Date 2019/11/10
 * @Time 10:33
 * 拦截器,需要注册
 */
public class MiniInterceptor implements HandlerInterceptor {
    @Resource
    RedisUtil redisUtil;

    @Override
    //拦截请求在调用接口之前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String userId=request.getHeader("userId");
        String userToken=request.getHeader("userToken");

        if(EmptyUtils.isNotEmpty(userId)&&EmptyUtils.isNotEmpty(userToken)){
            String key= Constants.USER_REDIS_SESSION+":"+userId;
            String uniqueToken= (String) redisUtil.get(key);//获取当前保存的token码
            System.out.println(redisUtil.get(key));
            if(EmptyUtils.isEmpty(uniqueToken)){//如果redis中没有数据
                System.out.println("请先登录...");
                returnErrorResponse(response,JSONResult.errorTokenMsg("请先登录"));
            }else if(!userToken.equals(uniqueToken)){//
                System.out.println("你已在别处登录");
                returnErrorResponse(response,JSONResult.errorTokenMsg("你已在别处登录"));
            }

        }else{
            System.out.println("请先登录...");
            returnErrorResponse(response,JSONResult.errorTokenMsg("请先登录"));
            return false;
        }

        //返回false，请求被拦截
        //返回true，请求ok
        return true;
    }
    //用于返回数据
    public void returnErrorResponse(HttpServletResponse response, JSONResult result)
            throws IOException, UnsupportedEncodingException {
        OutputStream out=null;
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
            out.flush();
        } finally{
            if(out!=null){
                out.close();
            }
        }
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
