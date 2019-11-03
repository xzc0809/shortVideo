package com.xzc.service.comments;
    import com.xzc.pojo.Comments;
    import java.util.List;
    import java.util.Map;
    import java.util.List;
    import java.util.Map;
    import com.xzc.common.Page;
    /**
    * Created by shang-pc on 2015/11/7.
    */
    public interface CommentsService {

    public Comments getCommentsById(Long id)throws Exception;

    public List<Comments>    getCommentsListByMap(Map
                                                          <String, Object> param)throws Exception;

    public Integer getCommentsCountByMap(Map
                                                 <String, Object> param)throws Exception;

    public Integer itriptxAddComments(Comments comments)throws Exception;

    public Integer itriptxModifyComments(Comments comments)throws Exception;

    public Integer itriptxDeleteCommentsById(Long id)throws Exception;

    public Page<Comments> queryCommentsPageByMap(Map
                                                         <String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
