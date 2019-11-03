package com.xzc.mapper;

import com.xzc.pojo.SearchRecords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface SearchRecordsMapper {

	public SearchRecords getSearchRecordsById(@Param(value = "id") Long id)throws Exception;

	public List<SearchRecords>	getSearchRecordsListByMap(Map<String, Object> param)throws Exception;

	public Integer getSearchRecordsCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertSearchRecords(SearchRecords searchRecords)throws Exception;

	public Integer updateSearchRecords(SearchRecords searchRecords)throws Exception;

	public Integer deleteSearchRecordsById(@Param(value = "id") Long id)throws Exception;

}
