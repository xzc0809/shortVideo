package com.xzc.controller;

import com.xzc.common.EmptyUtils;
import com.xzc.enums.VideoStatusEnum;
import com.xzc.pojo.Bgm;
import com.xzc.pojo.SearchRecords;
import com.xzc.pojo.Videos;
import com.xzc.service.bgm.BgmService;
import com.xzc.service.searchRecords.SearchRecordsService;
import com.xzc.service.users.UsersService;
import com.xzc.service.videos.VideosService;
import com.xzc.utils.FetchVideoCover;
import com.xzc.utils.JSONResult;
import com.xzc.utils.MergeVideoMp3;
import io.swagger.annotations.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    @Value("${file.ffmpegEXE}")
    String ffmpegEXE;
    @Autowired
    VideosService videosService;
    @Autowired
    BgmService bgmService;
    @Autowired
    SearchRecordsService searchRecordsService;

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

        if (EmptyUtils.isNotEmpty(bgmId)) {//bgm不为空

            Bgm bgm = bgmService.getBgmById(bgmId);
            String inputBgmPath = fileSpace + bgm.getPath();//音乐绝对地址
            MergeVideoMp3 tools = new MergeVideoMp3("H:\\tools\\ffmpeg\\bin\\ffmpeg.exe");//ffmpeg.exe文件路径  视频转换工具类

            String newVideoName = UUID.randomUUID().toString();//创建新名字
            uploadPathDB = "/" + userId + "/video" + "/" + newVideoName + ".mp4";//最终数据库相对路径
            /**
             * 先消除背景音
             * 输出视频newVideoName2
             * 再合成视频和音频newVideoName2+bgm
             * 最后输出视频newVideoName.mp4
             */
            String removeVoiceOutPath = fileSpace + "/" + userId + "/video" + "/" + newVideoName + "2" + ".mp4";//音频消除后的视频文件路径
            String inputVideoPath = finalPath;
            tools.removeVoice(inputVideoPath, removeVoiceOutPath);//消除背景音
            finalPath = fileSpace + uploadPathDB;//最后的文件合成输出路径
            tools.convertor(removeVoiceOutPath, inputBgmPath, videoSeconds, finalPath);//转换工具合成视频
//            tools.delFile(fileSpace+"/" + userId + "/video" ,newVideoName+"2"+".mp4");//删除被消除背景音的视频
            FetchVideoCover fetchVideoCover = new FetchVideoCover();
            fetchVideoCover.setFfmpegEXE(ffmpegEXE);//ffmpeg程序路径
            coverPathDB = "/" + userId + "/video/" + newVideoName + ".jpg";
            fetchVideoCover.getCover(finalPath, fileSpace + coverPathDB);

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

    @ApiOperation(value = "上传视频封面", notes = "上传用户视频的接口")
    @PostMapping(value = "/uploadCover", headers = "content-type=multipart/form-data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true,
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "videoId", value = "视频主键id", required = true,
                    dataType = "String", paramType = "form")

    })
    public JSONResult uploadCover(@ApiParam(value = "视频封面", required = true) MultipartFile file, String userId, String videoId) throws Exception {

        System.out.println("/video/uploadCover被调用");
        if (EmptyUtils.isEmpty(userId) || EmptyUtils.isEmpty(videoId)) {
            return JSONResult.errorMsg("用户id或视频id不能为空");//判空
        }
        //fileSpace 是文件上传的命名空间
        String uploadPathDB = null;//设置存储到数据库的相对路径
        String finalPath = null;//最终文件存储路径
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;

        try {
            if (file != null) {
                String filename = file.getOriginalFilename();//获取文件名
                if (EmptyUtils.isNotEmpty(filename)) {

                    uploadPathDB = "/" + userId + "/video" + filename;   //数据库相对路径
                    finalPath = fileSpace + uploadPathDB;//最终封面的绝对的存储地址
                    File outFile = new File(finalPath);

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

        System.out.println("finalPath:" + finalPath);
        System.out.println("uploadPath:" + uploadPathDB);
        //保存到数据库
        Videos videos = new Videos();
        videos.setCoverPath(finalPath);
        videos.setId(videoId);
        videosService.itriptxModifyVideos(videos);
        return JSONResult.ok(videos.getId());

    }

    @ApiOperation(value = "分页获取所有视频", notes = "分页")
    //, headers="content-type=multipart/form-data"
    @PostMapping(value = "/showAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isSaveRecord", value = "是否保存传过来的搜索记录", required = true,
                    dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true,
            dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "容量，默认为10",
                    dataType = "int", paramType = "query")

    })
    public JSONResult showAll(@RequestBody(required = false) Videos videos, @RequestParam(value = "isSaveRecord",defaultValue = "0", required = false) Integer isSaveRecord, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) throws Exception {

        Map map = new HashMap<>();
        //业务逻辑实现 当搜索后：1.获取前端获得的搜索词，判定是否需要保存isSaveRecord=1,然后根据这个搜索词模糊查询视频描述videosDesc，最后将获取的值传出
        //如果不搜索，则不传值，直接获取视频分页列表
        if (EmptyUtils.isNotEmpty(videos)) {
            map.put("videoDesc", videos.getVideoDesc());
        }
        if(isSaveRecord!=null&&isSaveRecord==1){//保存搜索词
            SearchRecords searchRecords=new SearchRecords();
            Sid sid=new Sid();
            searchRecords.setId(sid.nextShort());//设置唯一id
            searchRecords.setContent(videos.getVideoDesc());//设置热搜词
            searchRecordsService.itriptxAddSearchRecords(searchRecords);//保存到数据库
        }

        return JSONResult.ok(videosService.getVideosVoListByMap(pageNo, pageSize, map));
    }

    @ApiOperation(value = "获取热搜词", notes = "获取热搜词")
    //, headers="content-type=multipart/form-data"
    @PostMapping(value = "/getHotSearch")
    public JSONResult getHotSearch() throws Exception {

        return JSONResult.ok(searchRecordsService.getHotSearch());

    }


}
