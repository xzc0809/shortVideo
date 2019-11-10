package com.xzc.mapper;

import com.xzc.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
@Mapper
public interface UsersMapper {

	public Users getUsersById(@Param(value = "id") String id)throws Exception;

	public Users getUsersByUserName(@Param(value = "username") String username) throws Exception;
	public List<Users>getUsersListByMap(Map<String, Object> param)throws Exception;

	public Integer getUsersCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertUsers(Users users)throws Exception;

	public Integer updateUsers(Users users)throws Exception;

	public Integer deleteUsersById(@Param(value = "id") String id)throws Exception;

//增加用户收到的喜欢数
	public void addReceiveLikeCounts(String userId) throws Exception;
//减少用户收到的喜欢数
	public void reduceReceiveLikeCounts(String userId) throws Exception;

}
