package com.xzc.mapper;

import com.xzc.pojo.Bgm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface BgmMapper {

	public Bgm getBgmById(@Param(value = "id") String id)throws Exception;

	public List<Bgm>getBgmListByMap(Map<String, Object> param)throws Exception;

	public Integer getBgmCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertBgm(Bgm bgm)throws Exception;

	public Integer updateBgm(Bgm bgm)throws Exception;

	public Integer deleteBgmById(@Param(value = "id") Long id)throws Exception;

}
