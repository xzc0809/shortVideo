package com.xzc.controller;

import com.xzc.common.EmptyUtils;
import com.xzc.pojo.Users;
import com.xzc.pojo.Vo.UsersVo;
import com.xzc.service.users.UsersService;
import com.xzc.utils.JSONResult;
import com.xzc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @ApiOperation(value = "上传用户头像", notes = "上传用户头像的接口")
    @PostMapping(value = "/uploadFace")
    public JSONResult uploadFace(@RequestParam(value = "userId") String userId, @RequestParam(value = "file") MultipartFile[] files) throws IOException {

        //fileSpace 是文件上传的命名空间
        String uploadPathDB = "/" + userId + "/face";//存储到数据库的相对路径
        if(EmptyUtils.isEmpty(userId)){
            return JSONResult.errorMsg("用户id不能为空");
        }
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if (files != null && files.length != 0) {
                String filename = files[0].getOriginalFilename();//获取文件名
                if (EmptyUtils.isNotEmpty(filename)) {
                    String finalPath = fileSpace + uploadPathDB + "/" + filename;//获取 到最终的存储地址
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
        return JSONResult.ok();

    }


}
