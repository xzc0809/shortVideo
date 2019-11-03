package com.xzc.mapper;

import com.xzc.pojo.UsersFans;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface UsersFansMapper {

	public UsersFans getUsersFansById(@Param(value = "id") Long id)throws Exception;

	public List<UsersFans>	getUsersFansListByMap(Map<String, Object> param)throws Exception;

	public Integer getUsersFansCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertUsersFans(UsersFans usersFans)throws Exception;

	public Integer updateUsersFans(UsersFans usersFans)throws Exception;

	public Integer deleteUsersFansById(@Param(value = "id") Long id)throws Exception;

}
