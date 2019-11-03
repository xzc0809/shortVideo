package com.xzc.service.comments;
import com.xzc.mapper.CommentsMapper;
import com.xzc.pojo.Comments;
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
public class CommentsServiceImpl implements CommentsService {

    @Resource
    private CommentsMapper commentsMapper;

    public Comments getCommentsById(Long id)throws Exception{
        return commentsMapper.getCommentsById(id);
    }

    public List<Comments>	getCommentsListByMap(Map<String,Object> param)throws Exception{
        return commentsMapper.getCommentsListByMap(param);
    }

    public Integer getCommentsCountByMap(Map<String,Object> param)throws Exception{
        return commentsMapper.getCommentsCountByMap(param);
    }

    public Integer itriptxAddComments(Comments comments)throws Exception{

            return commentsMapper.insertComments(comments);
    }

    public Integer itriptxModifyComments(Comments comments)throws Exception{

        return commentsMapper.updateComments(comments);
    }

    public Integer itriptxDeleteCommentsById(Long id)throws Exception{
        return commentsMapper.deleteCommentsById(id);
    }

    public Page<Comments> queryCommentsPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = commentsMapper.getCommentsCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<Comments> commentsList = commentsMapper.getCommentsListByMap(param);
        page.setRows(commentsList);
        return page;
    }

}
