package com.xzc.mapper;

import com.xzc.pojo.Vo.CommentsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentsMapperCustom {

	public List<CommentsVO>	getCommentsVOListByMap(Map<String, Object> param)throws Exception;


}
