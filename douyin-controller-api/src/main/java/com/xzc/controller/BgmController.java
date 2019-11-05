package com.xzc.controller;

import com.xzc.service.bgm.BgmService;
import com.xzc.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaozhichao
 * @Date 2019/11/5
 * @Time 14:21
 */
@RestController
@Api(value = "获取BGM列表的接口",description = "bgm-api")
@RequestMapping("/bgm")
public class BgmController {

    @Autowired
    BgmService bgmService;

    @ApiOperation(value = "获取bgm列表的api" ,notes = "获取bgm列表接口")
    @PostMapping(value = "/getBgmList")
    public JSONResult getBgmList() throws Exception{
        Map map=new HashMap();
        List list=bgmService.getBgmListByMap(map);
        return JSONResult.ok(list);
    }

}
