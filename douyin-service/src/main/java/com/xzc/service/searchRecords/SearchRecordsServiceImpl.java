package com.xzc.service.searchRecords;
import com.xzc.mapper.SearchRecordsMapper;
import com.xzc.pojo.SearchRecords;
/////
import com.xzc.common.EmptyUtils;
import com.xzc.common.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.xzc.common.Constants;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SearchRecordsServiceImpl implements SearchRecordsService {

    @Resource
    private SearchRecordsMapper searchRecordsMapper;

    public SearchRecords getSearchRecordsById(Long id)throws Exception{
        return searchRecordsMapper.getSearchRecordsById(id);
    }

    public List<SearchRecords>	getSearchRecordsListByMap(Map<String,Object> param)throws Exception{
        return searchRecordsMapper.getSearchRecordsListByMap(param);
    }

    public Integer getSearchRecordsCountByMap(Map<String,Object> param)throws Exception{
        return searchRecordsMapper.getSearchRecordsCountByMap(param);
    }
    @Transactional(propagation = Propagation.REQUIRED)//事务管理
    public Integer itriptxAddSearchRecords(SearchRecords searchRecords)throws Exception{

            return searchRecordsMapper.insertSearchRecords(searchRecords);
    }

    public Integer itriptxModifySearchRecords(SearchRecords searchRecords)throws Exception{

        return searchRecordsMapper.updateSearchRecords(searchRecords);
    }

    public Integer itriptxDeleteSearchRecordsById(Long id)throws Exception{
        return searchRecordsMapper.deleteSearchRecordsById(id);
    }

    public Page<SearchRecords> querySearchRecordsPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = searchRecordsMapper.getSearchRecordsCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<SearchRecords> searchRecordsList = searchRecordsMapper.getSearchRecordsListByMap(param);
        page.setRows(searchRecordsList);
        return page;
    }

    @Override
    public List getHotSearch() throws Exception {
        return searchRecordsMapper.getHotSearch();
    }

}
