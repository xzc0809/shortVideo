package com.xzc.service.videos;
    import com.xzc.pojo.Videos;
    import java.util.List;
    import java.util.Map;
    import java.util.List;
    import java.util.Map;
    import com.xzc.common.Page;
    /**
    * Created by shang-pc on 2015/11/7.
    */
    public interface VideosService {

    public Videos getVideosById(Long id)throws Exception;

    public List<Videos>    getVideosListByMap(Map
                                                      <String, Object> param)throws Exception;

    public Integer getVideosCountByMap(Map
                                               <String, Object> param)throws Exception;

    public Integer itriptxAddVideos(Videos videos)throws Exception;

    public Integer itriptxModifyVideos(Videos videos)throws Exception;

    public Integer itriptxDeleteVideosById(Long id)throws Exception;

    public Page<Videos> queryVideosPageByMap(Map
                                                     <String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
