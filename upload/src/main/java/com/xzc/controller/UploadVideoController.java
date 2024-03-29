package com.xzc.controller;

import com.xzc.common.EmptyUtils;
import com.xzc.enums.VideoStatusEnum;
import com.xzc.pojo.Bgm;
import com.xzc.pojo.Videos;
import com.xzc.service.bgm.BgmService;
import com.xzc.service.comments.CommentsService;
import com.xzc.service.searchRecords.SearchRecordsService;
import com.xzc.service.users.UsersService;
import com.xzc.service.usersLikeVideos.UsersLikeVideosService;
import com.xzc.service.videos.VideosService;
import com.xzc.utils.JSONResult;
import com.xzc.utils.LinuxUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.n3r.idworker.Sid;
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
import java.util.Date;
import java.util.UUID;

/**
 * @author xiaozhichao
 * @Date 2019/11/19
 * @Time 15:22
 */
@RestController
@RequestMapping("uploadVideo")
public class UploadVideoController {

    @Autowired
    UsersService usersService;
    @Value("${file.space}")
    String fileSpace;//文件命名空间
    //    @Value("${file.ffmpegEXE}")
//    String ffmpegEXE;
    @Autowired
    VideosService videosService;
    @Autowired
    BgmService bgmService;
    @Autowired
    SearchRecordsService searchRecordsService;
    @Autowired
    UsersLikeVideosService usersLikeVideosService;
    @Autowired
    CommentsService commentsService;

    @ApiOperation(value = "上传用户视频", notes = "上传用户视频的接口")
    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户id", name = "userId", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "背景音乐id", name = "bgmId", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "width", name = "width", dataType = "int", paramType = "form", required = true),
            @ApiImplicitParam(value = "height", name = "height", dataType = "int", paramType = "form", required = true),
            @ApiImplicitParam(value = "秒数", name = "videoSeconds", dataType = "double", paramType = "form", required = true),
            @ApiImplicitParam(value = "视频描述", name = "desc", dataType = "String", paramType = "form"),

    })
    public JSONResult uploadVideo(String userId, @ApiParam(value = "短视频", required = true) MultipartFile file, @RequestParam(value = "bgmId", required = false) String bgmId, Integer width, Integer height, double videoSeconds, @RequestParam(value = "desc", required = false) String desc) throws Exception {

        System.out.println("/video/upload被调用");
        if (EmptyUtils.isEmpty(userId)) {
            return JSONResult.errorMsg("用户id不能为空");//判空
        }
        //fileSpace 是文件上传的命名空间
        String uploadPathDB = null;//设置存储到数据库的相对路径
        String finalPath = null;//最终文件存储路径
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
//        String videoOutputPath =null;
        String coverPathDB = null;
        try {
            if (file != null) {
                String filename = file.getOriginalFilename();//获取文件名
                if (EmptyUtils.isNotEmpty(filename)) {

                    //先把视频上传到本地，最后再根据有没有bgmId来转换合成视频
                    uploadPathDB = "/" + userId + "/video" + "/" + filename;   //数据库相对路径
                    finalPath = fileSpace + uploadPathDB;//最终视频的绝对的存储地址
                    File outFile = new File(finalPath);
                    LinuxUtils tools=new LinuxUtils();
                    //封面的数据库相对路径
                    coverPathDB = "/" + userId + "/video/" + filename + ".jpg";
                    //生成视频的封面
                    tools.getCover(finalPath, fileSpace + coverPathDB);

                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        outFile.getParentFile().mkdirs(); //创建父文件夹
                        outFile.createNewFile();
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

        if (EmptyUtils.isNotEmpty(bgmId)) {//bgm不为空

            Bgm bgm = bgmService.getBgmById(bgmId);
            String inputBgmPath = fileSpace + bgm.getPath();//获取音乐绝对地址
//            MergeVideoMp3 tools = new MergeVideoMp3(ffmpegEXE);//ffmpeg.exe文件路径  视频转换工具类  弃用

            String newVideoName = UUID.randomUUID().toString();//创建新名字
            uploadPathDB = "/" + userId + "/video" + "/" + newVideoName + ".mp4";//最终数据库相对路径
            /**
             * 先消除背景音
             * 输出视频newVideoName2
             * 再合成视频和音频newVideoName2+bgm
             * 最后输出视频newVideoName.mp4
             */
            //新建工具类
            LinuxUtils tools=new LinuxUtils();
            //获取音频消除后的视频文件路径
            String removeVoiceOutPath = fileSpace + "/" + userId + "/video" + "/" + newVideoName + "2" + ".mp4";
            //视频输出的地址
            String inputVideoPath = finalPath;
            //消除背景音
            tools.removeVoice(inputVideoPath, removeVoiceOutPath);
            //最后的文件合成输出路径
            finalPath = fileSpace + uploadPathDB;
            tools.convertor(removeVoiceOutPath, inputBgmPath, videoSeconds, finalPath);//转换工具合成视频
//            tools.delFile(fileSpace+"/" + userId + "/video" ,newVideoName+"2"+".mp4");//删除被消除背景音的视频

//            FetchVideoCover fetchVideoCover = new FetchVideoCover();
//            fetchVideoCover.setFfmpegEXE(ffmpegEXE);//ffmpeg程序路径

            //封面的数据库相对路径
            coverPathDB = "/" + userId + "/video/" + newVideoName + ".jpg";
            //生成视频的封面
            tools.getCover(finalPath, fileSpace + coverPathDB);

        }
        System.out.println("finalPath:" + finalPath);
        System.out.println("uploadPath:" + uploadPathDB);
        //保存到数据库
        Videos videos = new Videos();
        videos.setId(new Sid().nextShort());//唯一id
        videos.setUserId(userId);
        videos.setVideoPath(uploadPathDB);
        videos.setStatus(VideoStatusEnum.SUCCESS.value);//状态码
        videos.setAudioId(bgmId);
        videos.setVideoHeight(height);
        videos.setVideoWidth(width);
        videos.setVideoSeconds((float) videoSeconds);
        videos.setCreateTime(new Date());
        videos.setVideoDesc(desc);
        videos.setCoverPath(coverPathDB);//封面
        videos.setLikeCounts((long) 0);

        videosService.itriptxAddVideos(videos);
        return JSONResult.ok(videos.getId());

    }
}
