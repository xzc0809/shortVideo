package com.xzc.controller;

import com.xzc.common.EmptyUtils;
import com.xzc.pojo.Users;
import com.xzc.pojo.UsersReport;
import com.xzc.pojo.Vo.UsersVo;
import com.xzc.service.users.UsersService;
import com.xzc.service.usersFans.UsersFansService;
import com.xzc.service.usersReport.UsersReportService;
import com.xzc.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaozhichao
 * @Date 2019/11/3
 * @Time 15:53
 */
@RestController
@Api(value = "用户相关接口", description = "用户相关业务api")
@RequestMapping(value = "/user")

public class UserController extends BaseController {
    @Autowired
    UsersService usersService;
    @Value("${file.space}")
    String fileSpace;//文件命名空间
    @Autowired
    UsersFansService usersFansService;
    @Autowired
    UsersReportService usersReportService;

    @ApiOperation(value = "上传用户头像", notes = "上传用户头像的接口")
    @PostMapping(value = "/uploadFace")
    public JSONResult uploadFace(@RequestParam(value = "userId") String userId, @RequestParam(value = "file") MultipartFile[] files) throws Exception {



        if(EmptyUtils.isEmpty(userId)){
            return JSONResult.errorMsg("用户id不能为空");
        }
        //fileSpace 是文件上传的命名空间
        String uploadPathDB = null;//设置存储到数据库的相对路径
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if (files != null && files.length != 0) {
                String filename = files[0].getOriginalFilename();//获取文件名
                if (EmptyUtils.isNotEmpty(filename)) {
                    uploadPathDB="/" + userId + "/face"+"/"+filename;   //数据库相对路径
                    String finalPath = fileSpace + uploadPathDB ;//获取到最终的绝对的存储地址
                    File outFile = new File(finalPath);
                    System.out.println(finalPath);

                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        outFile.getParentFile().mkdirs(); //创建父文件夹
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = files[0].getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);

                }

            }else{
                return JSONResult.errorMsg("上传失败...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg("上传失败...");
        }finally {
            if (fileOutputStream!=null){
                fileOutputStream.flush();
                fileOutputStream.close();
            }

        }

        //保存到数据库
        Users modifyUsers=new Users();
        modifyUsers.setId(userId);
        modifyUsers.setFaceImage(uploadPathDB);
        usersService.itriptxModifyUsers(modifyUsers);

        return JSONResult.ok(uploadPathDB);

    }

    @ApiOperation(value = "查询用户信息", notes = "查询用户信息的api")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id",name="userId",paramType = "query",dataType = "String"),
            @ApiImplicitParam(value = "fanId",name="fanId",paramType = "query",dataType = "String"),

    })
    @PostMapping(value = "/queryUserInfo")
    public JSONResult queryUserInfo(@RequestParam(value = "userId") String userId,@RequestParam(value = "fanId",required = false) String fanId) throws Exception{

        if(EmptyUtils.isEmpty(userId)){
            return JSONResult.errorMsg("id不能为空");
        }

        Users users=usersService.getUsersById(userId);
        UsersVo usersVo=new UsersVo();

        if(EmptyUtils.isNotEmpty(fanId)){//判断关注关系
            Map map=new HashMap();
            map.put("userId",userId);
            map.put("fanId",fanId);

            if(usersFansService.getUsersFansCountByMap(map)!=0){
                usersVo.setFollow(true);
            }else{
                usersVo.setFollow(false);
            }
        }

        BeanUtils.copyProperties(users,usersVo);


        System.out.println("queryUserInfo被调用");
        return JSONResult.ok(usersVo);

    }

    @ApiOperation(value = "关注用户", notes = "关注用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id",name="userId",paramType = "query",dataType = "String"),
            @ApiImplicitParam(value = "粉丝id",name="fansId",paramType = "query",dataType = "String")
    })
    @PostMapping(value = "/addUserFansById")
    public JSONResult addUserFansById(@RequestParam(value = "userId") String userId,@RequestParam(value = "fansId") String fansId) throws Exception{

        if(EmptyUtils.isEmpty(userId)||EmptyUtils.isEmpty(fansId)){
            return JSONResult.errorMsg("id不能为空");
        }
        System.out.println("关注用户被调用：");
        //添加关注数 ，添加粉丝数，添加关系
        usersService.addFansCount(userId);
        usersService.addFollowsCount(fansId);
        usersFansService.itriptxAddUsersFans(userId,fansId);
        return JSONResult.ok();
    }

    @ApiOperation(value = "取消关注用户", notes = "关注用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id",name="userId",paramType = "query",dataType = "String"),
            @ApiImplicitParam(value = "粉丝id",name="fansId",paramType = "query",dataType = "String")
    })
    @PostMapping(value = "/delUserFansById")
    public JSONResult delUserFansById(@RequestParam(value = "userId") String userId,@RequestParam(value = "fansId") String fansId) throws Exception{

        if(EmptyUtils.isEmpty(userId)||EmptyUtils.isEmpty(fansId)){
            return JSONResult.errorMsg("id不能为空");
        }
        System.out.println("取消关注用户被调用：");
        //减少关注数 ，减少粉丝数，删除关系
        usersService.reduceFansCount(userId);
        usersService.reduceFollowsCount(fansId);
        usersFansService.delUsersFans(userId,fansId);
        return JSONResult.ok();
    }

    /**
     * 查询用户关系
     * @param userId
     * @param fansId
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询用户关系", notes = "查询用户关系")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id",name="userId",paramType = "query",dataType = "String"),
            @ApiImplicitParam(value = "粉丝id",name="fansId",paramType = "query",dataType = "String")
    })
    @PostMapping(value = "/queryUserFansRelationById")
    public JSONResult queryUserFansRelationById(@RequestParam(value = "userId") String userId,@RequestParam(value = "fansId") String fansId) throws Exception{

        if(EmptyUtils.isEmpty(userId)||EmptyUtils.isEmpty(fansId)){
            return JSONResult.errorMsg("id不能为空");
        }
        Map map=new HashMap();
        map.put("userId",userId);
        map.put("fanId",fansId);

        if(usersFansService.getUsersFansCountByMap(map)==1){
            return JSONResult.ok(true);
        }else{
            return JSONResult.ok(false);
        }
    }

    /**
     * 举报用户
     * @param usersReport
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "举报用户", notes = "举报用户")
    @PostMapping(value = "/reportUser")
    public JSONResult reportUser(@RequestBody UsersReport usersReport) throws Exception{
        usersReportService.itriptxAddUsersReport(usersReport);
        return JSONResult.ok();
    }

}
