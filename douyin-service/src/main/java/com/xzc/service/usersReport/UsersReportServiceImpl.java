package com.xzc.service.usersReport;
import com.xzc.mapper.UsersReportMapper;
import com.xzc.pojo.UsersReport;
/////
import com.xzc.common.EmptyUtils;
import com.xzc.common.Page;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.xzc.common.Constants;
@Service
public class UsersReportServiceImpl implements UsersReportService {

    @Resource
    private UsersReportMapper usersReportMapper;

    public UsersReport getUsersReportById(Long id)throws Exception{
        return usersReportMapper.getUsersReportById(id);
    }

    public List<UsersReport>	getUsersReportListByMap(Map<String,Object> param)throws Exception{
        return usersReportMapper.getUsersReportListByMap(param);
    }

    public Integer getUsersReportCountByMap(Map<String,Object> param)throws Exception{
        return usersReportMapper.getUsersReportCountByMap(param);
    }

    public Integer itriptxAddUsersReport(UsersReport usersReport)throws Exception{
            String sid=new Sid().nextShort();//添加主键
            usersReport.setId(sid);
            usersReport.setCreateDate(new Date());
            return usersReportMapper.insertUsersReport(usersReport);
    }

    public Integer itriptxModifyUsersReport(UsersReport usersReport)throws Exception{

        return usersReportMapper.updateUsersReport(usersReport);
    }

    public Integer itriptxDeleteUsersReportById(Long id)throws Exception{
        return usersReportMapper.deleteUsersReportById(id);
    }

    public Page<UsersReport> queryUsersReportPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = usersReportMapper.getUsersReportCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<UsersReport> usersReportList = usersReportMapper.getUsersReportListByMap(param);
        page.setRows(usersReportList);
        return page;
    }

}
