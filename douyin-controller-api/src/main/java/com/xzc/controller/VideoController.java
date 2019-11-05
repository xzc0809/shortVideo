package com.xzc.controller;

import com.xzc.common.EmptyUtils;
import com.xzc.pojo.Users;
import com.xzc.service.users.UsersService;
import com.xzc.service.videos.VideosService;
import com.xzc.utils.JSONResult;
import io.swagger.annotations.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author xiaozhichao
 * @Date 2019/11/5
 * @Time 20:43
 */
@RestController
@RequestMapping(value = "/video")
@Api(value = "视频api", description = "视频api")
public class VideoController {
    @Autowired
    UsersService usersService;
    @Value("${file.space}")
    String fileSpace;//文件命名空间
    @Autowired
    VideosService videosService;

    @ApiOperation(value = "上传用户视频", notes = "上传用户视频的接口")
    @PostMapping(value = "/upload", headers="content-type=multipart/form-data")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id", name = "userId", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(value = "背景音乐id", name = "bgmId", dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "width", name = "width", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(value = "height", name = "height", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(value = "秒数", name = "videoSeconds", dataType = "double", paramType = "query", required = true),
            @ApiImplicitParam(value = "视频描述", name = "desc", dataType = "String", paramType = "query"),


    })
    public JSONResult uploadVideo(String userId, @ApiParam(value="短视频", required=true) MultipartFile file, @RequestParam(value = "bgmId",required = false)String bgmId, Integer width, Integer height, Double videoSeconds, @RequestParam(value = "desc",required = false) String desc) throws Exception {


        if (EmptyUtils.isEmpty(userId)) {
            return JSONResult.errorMsg("用户id不能为空");//判空
        }
        //fileSpace 是文件上传的命名空间
        String uploadPathDB = null;//设置存储到数据库的相对路径
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if (file != null) {
                String filename = file.getOriginalFilename();//获取文件名
                if (EmptyUtils.isNotEmpty(filename)) {

                    uploadPathDB = "/" + userId + "/video" + "/" + filename;   //数据库相对路径
                    String finalPath = fileSpace + uploadPathDB;//最终的绝对的存储地址
                    File outFile = new File(finalPath);
                    System.out.println(finalPath);

                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        outFile.getParentFile().mkdirs(); //创建父文件夹
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);

                }

            } else {
                return JSONResult.errorMsg("上传失败...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg("上传失败...");
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }

        }

        //保存到数据库


        return JSONResult.ok();

    }

}
