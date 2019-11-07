package com.xzc.service.bgm;
import com.xzc.mapper.BgmMapper;
import com.xzc.pojo.Bgm;
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
public class BgmServiceImpl implements BgmService {

    @Resource
    private BgmMapper bgmMapper;

    public Bgm getBgmById(String id)throws Exception{
        return bgmMapper.getBgmById(id);
    }

    public List<Bgm>getBgmListByMap(Map<String,Object> param)throws Exception{
        return bgmMapper.getBgmListByMap(param);
    }

    public Integer getBgmCountByMap(Map<String,Object> param)throws Exception{
        return bgmMapper.getBgmCountByMap(param);
    }

    public Integer itriptxAddBgm(Bgm bgm)throws Exception{

            return bgmMapper.insertBgm(bgm);
    }

    public Integer itriptxModifyBgm(Bgm bgm)throws Exception{

        return bgmMapper.updateBgm(bgm);
    }

    public Integer itriptxDeleteBgmById(Long id)throws Exception{
        return bgmMapper.deleteBgmById(id);
    }

    public Page<Bgm> queryBgmPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = bgmMapper.getBgmCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<Bgm> bgmList = bgmMapper.getBgmListByMap(param);
        page.setRows(bgmList);
        return page;
    }

}
