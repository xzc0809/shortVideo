package com.xzc.service.usersLikeVideos;
    import com.xzc.pojo.UsersLikeVideos;
    import java.util.List;
    import java.util.Map;
    import java.util.List;
    import java.util.Map;
    import com.xzc.common.Page;
    /**
    * Created by shang-pc on 2015/11/7.
    */
    public interface UsersLikeVideosService {

    public UsersLikeVideos getUsersLikeVideosById(Long id)throws Exception;

    public List<UsersLikeVideos> getUsersLikeVideosListByMap(Map<String, Object> param)throws Exception;

    public Integer getUsersLikeVideosCountByMap(Map<String, Object> param)throws Exception;

    public Integer itriptxAddUsersLikeVideos(UsersLikeVideos usersLikeVideos)throws Exception;

    public Integer itriptxModifyUsersLikeVideos(UsersLikeVideos usersLikeVideos)throws Exception;

    public Integer itriptxDeleteUsersLikeVideosById(Long id)throws Exception;

    public Page<UsersLikeVideos> queryUsersLikeVideosPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
