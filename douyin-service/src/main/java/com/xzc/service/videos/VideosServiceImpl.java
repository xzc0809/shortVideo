package com.xzc.service.videos;
import com.xzc.mapper.UsersFansMapper;
import com.xzc.mapper.UsersLikeVideosMapper;
import com.xzc.mapper.VideosMapper;
import com.xzc.mapper.VideosMapperCustom;
import com.xzc.pojo.Videos;
/////
import com.xzc.common.EmptyUtils;
import com.xzc.common.Page;
import com.xzc.pojo.Vo.VideosVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.xzc.common.Constants;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VideosServiceImpl implements VideosService {

    @Resource
    private VideosMapper videosMapper;
    @Resource
    private VideosMapperCustom videosMapperCustom;
    @Resource
    private UsersLikeVideosMapper usersLikeVideosMapper;
    @Resource
    private UsersFansMapper usersFansMapper;

    public Videos getVideosById(Long id)throws Exception{
        return videosMapper.getVideosById(id);
    }

    public List<Videos>	getVideosListByMap(Map<String,Object> param)throws Exception{
        return videosMapper.getVideosListByMap(param);
    }

    public Integer getVideosCountByMap(Map<String,Object> param)throws Exception{
        return videosMapper.getVideosCountByMap(param);
    }

    @Transactional(propagation = Propagation.REQUIRED)//事务管理
    public Integer itriptxAddVideos(Videos videos)throws Exception{

            return videosMapper.insertVideos(videos);
    }

    public Integer itriptxModifyVideos(Videos videos)throws Exception{

        return videosMapper.updateVideos(videos);
    }

    public Integer itriptxDeleteVideosById(Long id)throws Exception{
        return videosMapper.deleteVideosById(id);
    }

    public Page<Videos> queryVideosPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = videosMapper.getVideosCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<Videos> videosList = videosMapper.getVideosListByMap(param);
        page.setRows(videosList);
        return page;
    }

    @Override
    public Page<VideosVo> getVideosVoListByMap(Integer pageNo,Integer pageSize,Map<String, Object> param) throws Exception {
        Integer total = videosMapper.getVideosCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<VideosVo> videosList = videosMapperCustom.getVideosVoListByMap(param);
        page.setRows(videosList);
        return page;

    }

    @Override
    public void reduceVideoLikeCount(String videoId) throws Exception {
        videosMapper.reduceVideoLikeCount(videoId);
    }

    @Override
    public void addVideoLikeCount(String videoId) throws Exception {
        videosMapper.addVideoLikeCount(videoId);
    }

    //获取喜欢的视频
    @Override
    public Page queryUserLikeVideos(Integer pageNo,Integer pageSize,Map param) throws Exception {
        Integer total = usersLikeVideosMapper.getUsersLikeVideosCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<VideosVo> videosList = videosMapperCustom.queryUserLikeVideos(param);
        page.setRows(videosList);
        return page;
    }

    @Override
    public Page queryUserFollowVideos(Integer pageNo,Integer pageSize,Map param) throws Exception {
        Integer total = usersFansMapper.getUsersFansCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<VideosVo> videosList = videosMapperCustom.queryUserFollowVideos(param);
        page.setRows(videosList);
        return page;
    }
}
