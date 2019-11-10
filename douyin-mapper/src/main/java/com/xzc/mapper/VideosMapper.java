package com.xzc.mapper;

import com.xzc.pojo.Videos;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
@Mapper
public interface VideosMapper {

	public Videos getVideosById(@Param(value = "id") Long id)throws Exception;

	public List<Videos>	getVideosListByMap(Map<String, Object> param)throws Exception;

	public Integer getVideosCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertVideos(Videos videos)throws Exception;

	public Integer updateVideos(Videos videos)throws Exception;

	public Integer deleteVideosById(@Param(value = "id") Long id)throws Exception;

	public void reduceVideoLikeCount(String videoId) throws Exception;
	public void addVideoLikeCount(String videoId) throws Exception;
}
