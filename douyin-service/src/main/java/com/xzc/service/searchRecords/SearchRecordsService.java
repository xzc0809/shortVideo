package com.xzc.service.searchRecords;

import com.xzc.pojo.SearchRecords;

import java.util.List;
import java.util.Map;
import java.util.List;
import java.util.Map;

import com.xzc.common.Page;

/**
 * Created by shang-pc on 2015/11/7.
 */
public interface SearchRecordsService {

    public SearchRecords getSearchRecordsById(Long id) throws Exception;

    public List<SearchRecords> getSearchRecordsListByMap(Map<String, Object> param) throws Exception;

    public Integer getSearchRecordsCountByMap(Map<String, Object> param) throws Exception;

    public Integer itriptxAddSearchRecords(SearchRecords searchRecords) throws Exception;

    public Integer itriptxModifySearchRecords(SearchRecords searchRecords) throws Exception;

    public Integer itriptxDeleteSearchRecordsById(Long id) throws Exception;

    public Page<SearchRecords> querySearchRecordsPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception;
}
