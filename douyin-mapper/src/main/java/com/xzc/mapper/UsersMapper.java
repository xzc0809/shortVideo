package com.xzc.mapper;

import com.xzc.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
@Mapper
public interface UsersMapper {

	public Users getUsersById(@Param(value = "id") Long id)throws Exception;

	public List<Users>	getUsersListByMap(Map<String, Object> param)throws Exception;

	public Integer getUsersCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertUsers(Users users)throws Exception;

	public Integer updateUsers(Users users)throws Exception;

	public Integer deleteUsersById(@Param(value = "id") Long id)throws Exception;

}
