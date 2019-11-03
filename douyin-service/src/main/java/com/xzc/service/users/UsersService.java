package com.xzc.service.users;
    import com.xzc.pojo.Users;
    import java.util.List;
    import java.util.Map;
    import java.util.List;
    import java.util.Map;
    import com.xzc.common.Page;
    /**
    * Created by shang-pc on 2015/11/7.
    */
    public interface UsersService {

    public Users getUsersById(Long id)throws Exception;

    public Users getUsersByUserName( String username) throws Exception;

    public List<Users> getUsersListByMap(Map<String, Object> param)throws Exception;

    public Integer getUsersCountByMap(Map<String, Object> param)throws Exception;

    public Integer itriptxAddUsers(Users users)throws Exception;

    public Integer itriptxModifyUsers(Users users)throws Exception;

    public Integer itriptxDeleteUsersById(Long id)throws Exception;

    public Page<Users> queryUsersPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
