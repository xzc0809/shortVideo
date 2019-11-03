package com.xzc.controller;

import com.xzc.service.users.UsersService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaozhichao
 * @Date 2019/11/3
 * @Time 11:00
 */
@RestController
@Api(value = "helloworld测试")
public class HelloWorld {
    @Autowired
    UsersService usersService;
    @RequestMapping(value = "/hello")
    public String hello(){
        return "helloworld";
    }
    @RequestMapping(value = "/hello2")
    public String hello2() throws Exception{
        return usersService.getUsersById((long)1001).getNickname();
    }

}
