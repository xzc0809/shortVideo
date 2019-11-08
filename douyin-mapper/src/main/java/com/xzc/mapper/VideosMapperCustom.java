package com.xzc.mapper;

import com.xzc.pojo.Videos;
import com.xzc.pojo.Vo.VideosVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author xiaozhichao
 * @Date 2019/11/8
 * @Time 15:44
 * Vo mapper
 */
@Mapper
public interface VideosMapperCustom {

    public List<VideosVo> getVideosVoListByMap(Map<String, Object> param)throws Exception;

}
