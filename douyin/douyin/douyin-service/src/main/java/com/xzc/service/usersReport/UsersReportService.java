package com.xzc.service.usersReport;
    import com.xzc.pojo.UsersReport;
    import java.util.List;
    import java.util.Map;
    import java.util.List;
    import java.util.Map;
    import com.xzc.common.Page;
    /**
    * Created by shang-pc on 2015/11/7.
    */
    public interface UsersReportService {

    public UsersReport getUsersReportById(Long id)throws Exception;

    public List<UsersReport>    getUsersReportListByMap(Map
                                                                <String, Object> param)throws Exception;

    public Integer getUsersReportCountByMap(Map
                                                    <String, Object> param)throws Exception;

    public Integer itriptxAddUsersReport(UsersReport usersReport)throws Exception;

    public Integer itriptxModifyUsersReport(UsersReport usersReport)throws Exception;

    public Integer itriptxDeleteUsersReportById(Long id)throws Exception;

    public Page<UsersReport> queryUsersReportPageByMap(Map
                                                               <String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
