package com.xzc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaozhichao
 * @Date 2019/11/19
 * @Time 15:18
 */
@RestController
public class Test {
    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
