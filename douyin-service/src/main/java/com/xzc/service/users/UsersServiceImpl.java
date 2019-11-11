package com.xzc.service.users;

import com.xzc.mapper.UsersMapper;
import com.xzc.pojo.Users;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersMapper usersMapper;

    public Users getUsersById(String id) throws Exception {
        return usersMapper.getUsersById(id);
    }

    public Users getUsersByUserName(String username) throws Exception {
        return usersMapper.getUsersByUserName(username);
    }

    public List<Users> getUsersListByMap(Map<String, Object> param) throws Exception {
        return usersMapper.getUsersListByMap(param);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getUsersCountByMap(Map<String, Object> param) throws Exception {
        return usersMapper.getUsersCountByMap(param);
    }

    @Transactional(propagation = Propagation.REQUIRED)//事务管理
    public Integer itriptxAddUsers(Users users) throws Exception {
        return usersMapper.insertUsers(users);
    }

    @Transactional(propagation = Propagation.REQUIRED)//事务管理
    public Integer itriptxModifyUsers(Users users) throws Exception {

        return usersMapper.updateUsers(users);
    }

    public Integer itriptxDeleteUsersById(String id) throws Exception {
        return usersMapper.deleteUsersById(id);
    }

    public Page<Users> queryUsersPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        Integer total = usersMapper.getUsersCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<Users> usersList = usersMapper.getUsersListByMap(param);
        page.setRows(usersList);
        return page;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)//事务管理
    public void addReceiveLikeCounts(String userId) throws Exception {
        usersMapper.addReceiveLikeCounts(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)//事务管理
    public void reduceReceiveLikeCounts(String userId) throws Exception {
        usersMapper.reduceReceiveLikeCounts(userId);
    }

    @Transactional(propagation = Propagation.REQUIRED)//事务管理
    @Override
    public void addFollowsCount(String userId) throws Exception {
        usersMapper.addFollowsCount(userId);
    }

    @Transactional(propagation = Propagation.REQUIRED)//事务管理
    @Override
    public void reduceFollowsCount(String userId) throws Exception {
        usersMapper.reduceFollowsCount(userId);
    }

    @Transactional(propagation = Propagation.REQUIRED)//事务管理
    @Override
    public void addFansCount(String userId) throws Exception {
        usersMapper.addFansCount(userId);
    }

    @Transactional(propagation = Propagation.REQUIRED)//事务管理
    @Override
    public void reduceFansCount(String userId) throws Exception {
        usersMapper.reduceFansCount(userId);
    }

}
