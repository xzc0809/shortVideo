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

    public Users getUsersById(String id) throws Exception;

    public Users getUsersByUserName(String username) throws Exception;

    public List<Users> getUsersListByMap(Map<String, Object> param) throws Exception;

    public Integer getUsersCountByMap(Map<String, Object> param) throws Exception;

    public Integer itriptxAddUsers(Users users) throws Exception;

    public Integer itriptxModifyUsers(Users users) throws Exception;

    public Integer itriptxDeleteUsersById(String id) throws Exception;

    public Page<Users> queryUsersPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception;

    //收到喜欢的数的增加 和减少
    public void addReceiveLikeCounts(String userId) throws Exception;

    public void reduceReceiveLikeCounts(String userId) throws Exception;

    //添加关注数
    public void addFollowsCount(String userId) throws Exception;

    //减少关注数
    public void reduceFollowsCount(String userId) throws Exception;

    //添加粉丝数
    public void addFansCount(String userId) throws Exception;

    //减少粉丝数
    public void reduceFansCount(String userId) throws Exception;
}
