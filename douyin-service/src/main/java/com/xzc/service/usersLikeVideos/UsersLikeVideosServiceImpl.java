package com.xzc.service.usersLikeVideos;
import com.xzc.mapper.UsersLikeVideosMapper;
import com.xzc.pojo.UsersLikeVideos;
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
@Service
public class UsersLikeVideosServiceImpl implements UsersLikeVideosService {

    @Resource
    private UsersLikeVideosMapper usersLikeVideosMapper;

    public UsersLikeVideos getUsersLikeVideosById(Long id)throws Exception{
        return usersLikeVideosMapper.getUsersLikeVideosById(id);
    }

    public List<UsersLikeVideos>	getUsersLikeVideosListByMap(Map<String,Object> param)throws Exception{
        return usersLikeVideosMapper.getUsersLikeVideosListByMap(param);
    }

    public Integer getUsersLikeVideosCountByMap(Map<String,Object> param)throws Exception{
        return usersLikeVideosMapper.getUsersLikeVideosCountByMap(param);
    }

    public Integer itriptxAddUsersLikeVideos(UsersLikeVideos usersLikeVideos)throws Exception{

            return usersLikeVideosMapper.insertUsersLikeVideos(usersLikeVideos);
    }

    public Integer itriptxModifyUsersLikeVideos(UsersLikeVideos usersLikeVideos)throws Exception{

        return usersLikeVideosMapper.updateUsersLikeVideos(usersLikeVideos);
    }

    public Integer itriptxDeleteUsersLikeVideosById(Long id)throws Exception{
        return usersLikeVideosMapper.deleteUsersLikeVideosById(id);
    }

    public Page<UsersLikeVideos> queryUsersLikeVideosPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = usersLikeVideosMapper.getUsersLikeVideosCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<UsersLikeVideos> usersLikeVideosList = usersLikeVideosMapper.getUsersLikeVideosListByMap(param);
        page.setRows(usersLikeVideosList);
        return page;
    }

}