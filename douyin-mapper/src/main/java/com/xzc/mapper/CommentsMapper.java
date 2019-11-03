package com.xzc.mapper;

import com.xzc.pojo.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface CommentsMapper {

	public Comments getCommentsById(@Param(value = "id") Long id)throws Exception;

	public List<Comments>	getCommentsListByMap(Map<String, Object> param)throws Exception;

	public Integer getCommentsCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertComments(Comments comments)throws Exception;

	public Integer updateComments(Comments comments)throws Exception;

	public Integer deleteCommentsById(@Param(value = "id") Long id)throws Exception;

}
