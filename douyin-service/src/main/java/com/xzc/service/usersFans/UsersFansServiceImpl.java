package com.xzc.service.usersFans;
import com.xzc.mapper.UsersFansMapper;
import com.xzc.pojo.UsersFans;
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
public class UsersFansServiceImpl implements UsersFansService {

    @Resource
    private UsersFansMapper usersFansMapper;

    public UsersFans getUsersFansById(Long id)throws Exception{
        return usersFansMapper.getUsersFansById(id);
    }

    public List<UsersFans>	getUsersFansListByMap(Map<String,Object> param)throws Exception{
        return usersFansMapper.getUsersFansListByMap(param);
    }

    public Integer getUsersFansCountByMap(Map<String,Object> param)throws Exception{
        return usersFansMapper.getUsersFansCountByMap(param);
    }

    @Transactional(propagation = Propagation.REQUIRED)//事务管理
    public Integer itriptxAddUsersFans(String userId,String fansId)throws Exception{
            UsersFans usersFans=new UsersFans();
            String sid=new Sid().nextShort();
            usersFans.setId(sid);
            usersFans.setFanId(fansId);
            usersFans.setUserId(userId);
            return usersFansMapper.insertUsersFans(usersFans);
    }

    public Integer itriptxModifyUsersFans(UsersFans usersFans)throws Exception{

        return usersFansMapper.updateUsersFans(usersFans);
    }

    public Integer itriptxDeleteUsersFansById(Long id)throws Exception{
        return usersFansMapper.deleteUsersFansById(id);
    }

    public Page<UsersFans> queryUsersFansPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = usersFansMapper.getUsersFansCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<UsersFans> usersFansList = usersFansMapper.getUsersFansListByMap(param);
        page.setRows(usersFansList);
        return page;
    }

    @Transactional(propagation = Propagation.REQUIRED)//事务管理
    public void delUsersFans(String userId,String fansId)throws Exception{
        //删除粉丝关系
        usersFansMapper.delUsersFans(userId,fansId);
    }

}
