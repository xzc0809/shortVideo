package com.xzc.controller;

import com.xzc.common.RedisUtil;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xiaozhichao
 * @Date 2019/11/3
 * @Time 22:18
 */
@RestController
public class BaseController {
    @Resource
    RedisUtil redisUtil;

    public static final String USER_REDIS_SESSION="USER_REDIS_SESSION";


}
