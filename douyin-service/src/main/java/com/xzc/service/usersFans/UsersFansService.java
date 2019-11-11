package com.xzc.service.usersFans;
    import com.xzc.pojo.UsersFans;
    import java.util.List;
    import java.util.Map;
    import java.util.List;
    import java.util.Map;
    import com.xzc.common.Page;
    /**
    * Created by shang-pc on 2015/11/7.
    */
    public interface UsersFansService {

    public UsersFans getUsersFansById(Long id)throws Exception;

    public List<UsersFans>    getUsersFansListByMap(Map<String, Object> param)throws Exception;

    public Integer getUsersFansCountByMap(Map<String, Object> param)throws Exception;

    public Integer itriptxAddUsersFans(String userId,String fansId)throws Exception;

    public Integer itriptxModifyUsersFans(UsersFans usersFans)throws Exception;

    public Integer itriptxDeleteUsersFansById(Long id)throws Exception;

    public Page<UsersFans> queryUsersFansPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;

    public void delUsersFans(String userId,String fansId)throws Exception;
    }
