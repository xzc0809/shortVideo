package com.xzc.service.bgm;
    import com.xzc.pojo.Bgm;
    import java.util.List;
    import java.util.Map;
    import java.util.List;
    import java.util.Map;
    import com.xzc.common.Page;
    /**
    * Created by shang-pc on 2015/11/7.
    */
    public interface BgmService {

    public Bgm getBgmById(Long id)throws Exception;

    public List<Bgm>    getBgmListByMap(Map
                                                <String, Object> param)throws Exception;

    public Integer getBgmCountByMap(Map
                                            <String, Object> param)throws Exception;

    public Integer itriptxAddBgm(Bgm bgm)throws Exception;

    public Integer itriptxModifyBgm(Bgm bgm)throws Exception;

    public Integer itriptxDeleteBgmById(Long id)throws Exception;

    public Page<Bgm> queryBgmPageByMap(Map
                                               <String, Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
