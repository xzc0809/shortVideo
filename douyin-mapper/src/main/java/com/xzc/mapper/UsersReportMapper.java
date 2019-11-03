package com.xzc.mapper;

import com.xzc.pojo.UsersReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
@Mapper
public interface UsersReportMapper {

	public UsersReport getUsersReportById(@Param(value = "id") Long id)throws Exception;

	public List<UsersReport>	getUsersReportListByMap(Map<String, Object> param)throws Exception;

	public Integer getUsersReportCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertUsersReport(UsersReport usersReport)throws Exception;

	public Integer updateUsersReport(UsersReport usersReport)throws Exception;

	public Integer deleteUsersReportById(@Param(value = "id") Long id)throws Exception;

}
