package com.xzc.controller;

import com.xzc.common.Dto;
import com.xzc.common.DtoUtil;
import com.xzc.common.EmptyUtils;
import com.xzc.common.RedisUtil;
import com.xzc.config.RedisConfig;
import com.xzc.pojo.Users;
import com.xzc.pojo.Vo.UsersVo;
import com.xzc.service.users.UsersService;
import com.xzc.utils.JSONResult;
import com.xzc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author xiaozhichao
 * @Date 2019/11/3
 * @Time 15:53
 */
@RestController
@Api(value = "注册登录的api",description = "注册登录api")
public class RegisterLoginController extends BaseController{
    @Autowired
    UsersService usersService;
    @ApiOperation(value = "注册接口",notes = "注册的接口")
    @PostMapping(value = "/regist")
    public JSONResult regist(@RequestBody Users users)  throws Exception{
        if (EmptyUtils.isEmpty(users.getUsername())||EmptyUtils.isEmpty(users.getPassword())){
            return JSONResult.errorMsg("用户名或密码不能为空");
        }else{
            Map map=new HashMap();
            map.put("username",users.getUsername());
            if (usersService.getUsersCountByMap(map)!=0){
                return JSONResult.errorMsg("用户名已存在");
            }else{
                String sid=new Sid().nextShort();//设置全局唯一id
                users.setId(sid);
                users.setPassword(MD5Utils.getMD5Str(users.getPassword()));//密码使用md5加密算法加密
                users.setReceiveLikeCounts(0);
                users.setNickname(users.getUsername());
                users.setUsername(users.getUsername());
                users.setFansCounts(0);
                users.setFollowCounts(0);
                usersService.itriptxAddUsers(users);//保存用户

                users.setPassword(null);
                return JSONResult.ok(setUserToken(users));//返回vo类
            }

        }


    }

    @PostMapping(value = "/login")
    public JSONResult login(@RequestBody Users users) throws Exception{
        String username=users.getUsername();
        String password=users.getPassword();

        if (EmptyUtils.isEmpty(username)||EmptyUtils.isEmpty(password)){
            return JSONResult.errorMsg("用户名或密码为空");
        }else{
            Map map=new HashMap();
            map.put("username",username);
            if(usersService.getUsersCountByMap(map)!=0){  //用户名存在
                map.put("password",MD5Utils.getMD5Str(password));
                if (usersService.getUsersByUserName(username)!=null){  //验证密码
                    Users user2=usersService.getUsersByUserName(username);
                    user2.setPassword(null);
                    return JSONResult.ok(setUserToken(user2));
                }else{
                    return JSONResult.errorMsg("用户名或密码错误");
                }

            }else{
                return JSONResult.build(201,"用户不存在",null);
            }
        }

    }

    @ApiOperation(value ="用户注销",notes = "用户注销api")
    @PostMapping(value = "/logout")
    @ApiImplicitParam(value = "用户id",name="userId",paramType = "query",dataType = "String",required = true)
    public JSONResult logout(String userId){
            redisUtil.del(USER_REDIS_SESSION+":"+userId);
            return JSONResult.ok();
    }

    private UsersVo setUserToken(Users userModel){
        String uniqueToken=UUID.randomUUID().toString();//设置token
        redisUtil.set(USER_REDIS_SESSION+":"+userModel.getId(),uniqueToken,1000*60*30);

        UsersVo usersVo=new UsersVo();
        BeanUtils.copyProperties(userModel,usersVo);//将users对象值拷贝给vo

        usersVo.setUserToken(uniqueToken) ;
        return usersVo;
    }

}
