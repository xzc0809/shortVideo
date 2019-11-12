package com.xzc.service.videos;
    import com.xzc.pojo.Videos;
    import java.util.List;
    import java.util.Map;
    import java.util.List;
    import java.util.Map;
    import com.xzc.common.Page;
    import com.xzc.pojo.Vo.VideosVo;

/**
    * Created by shang-pc on 2015/11/7.
    */
    public interface VideosService {

    public Videos getVideosById(Long id)throws Exception;

    public List<Videos>   getVideosListByMap(Map<String, Object> param)throws Exception;

    public Integer getVideosCountByMap(Map<String, Object> param)throws Exception;

    public Integer itriptxAddVideos(Videos videos)throws Exception;

    public Integer itriptxModifyVideos(Videos videos)throws Exception;

    public Integer itriptxDeleteVideosById(Long id)throws Exception;

    public Page<Videos> queryVideosPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
    //VO的实现方法
    public Page<VideosVo> getVideosVoListByMap(Integer pageNo,Integer pageSize,Map<String, Object> param)throws Exception;

    public void reduceVideoLikeCount(String videoId) throws Exception;
    public void addVideoLikeCount(String videoId) throws Exception;

    //获取用户喜欢的视频
    public Page queryUserLikeVideos(Integer pageNo,Integer pageSize,Map map) throws Exception;
    //获取关注的人发布的视频
    public Page queryUserFollowVideos(Integer pageNo,Integer pageSize,Map<String, Object> map) throws Exception;
    }
