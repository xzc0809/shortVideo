package com.xzc.config;

import com.xzc.controller.interceptro.MiniInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * @author xiaozhichao
 * @Date 2019/11/4
 * @Time 19:06
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {//右键source，重写或实现
        String os = System.getProperty("os.name");

        if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统

            registry.addResourceHandler("/**")
                    .addResourceLocations("classpath:/META-INF/resources/")
//                   .addResourceLocations("file:xiaozc.xyz:8086/userFiles/");
                    .addResourceLocations("file:H:\\gitReponsitory\\douyin\\douyin_userFiles\\");//映射文件夹

        } else {  //linux 和mac
            registry.addResourceHandler("/**")
                    .addResourceLocations("classpath:/META-INF/resources/")  //swagger2页面;
                .addResourceLocations("file:/root/douyin/www/userFiles/");   //媒体资源
        }

//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/META-INF/resources/")
//                .addResourceLocations("file:xiaozc.xyz:8086/userFiles/");
        //                .addResourceLocations("file:H:\\gitReponsitory\\douyin\\douyin_userFiles\\");//映射文件夹
    }


    /**
     * 注册拦截器
     * @return
     */
    @Bean
    public MiniInterceptor miniInterceptor(){
        return new MiniInterceptor();
    }

    /**
     * 添加拦截器 alt+shift+s override addInterceptors
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
//        registry.addInterceptor(miniInterceptor()).addPathPatterns("/user/**");//填写拦截路径,接口路径
        registry.addInterceptor(miniInterceptor()).addPathPatterns("/user/**")
                .addPathPatterns("/uploadVideo/upload", "/uploadVideo/uploadCover")
//                        ,
//                        "/video/userLike", "/video/userUnLike",
//                        "/video/saveComment")
//                .addPathPatterns("/bgm/**")
                .excludePathPatterns("/user/queryPublisher");
    }
}
