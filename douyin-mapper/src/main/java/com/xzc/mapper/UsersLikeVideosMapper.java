package com.xzc.mapper;

import com.xzc.pojo.UsersLikeVideos;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
@Mapper
public interface UsersLikeVideosMapper {

	public UsersLikeVideos getUsersLikeVideosById(@Param(value = "id") Long id)throws Exception;

	public List<UsersLikeVideos>getUsersLikeVideosListByMap(Map<String, Object> param)throws Exception;

	public Integer getUsersLikeVideosCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertUsersLikeVideos(UsersLikeVideos usersLikeVideos)throws Exception;

	public Integer updateUsersLikeVideos(UsersLikeVideos usersLikeVideos)throws Exception;

	public Integer deleteUsersLikeVideosById(@Param(value = "id") Long id)throws Exception;

	//删除用户喜欢的视频
	public void deleteUsersLikeVideos(@Param(value="userId") String userId,@Param(value = "videoId") String video_id) throws Exception;

}
