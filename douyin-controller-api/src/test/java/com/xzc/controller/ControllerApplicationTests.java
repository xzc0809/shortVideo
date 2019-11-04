package com.xzc.controller;

import com.xzc.service.users.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ControllerApplicationTests {

    @Autowired
    UsersService usersService;
    @Test
    void contextLoads() {
    }

    @Test
    /**
     * 数据库连接测试
     */
    public void test1() throws Exception{
        System.out.println(usersService.getUsersById("1001").getNickname());
    }


}
