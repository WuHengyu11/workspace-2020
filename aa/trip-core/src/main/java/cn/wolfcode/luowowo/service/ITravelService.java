package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.Travel;
import cn.wolfcode.luowowo.query.TravelQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 游记服务接口
 */
public interface ITravelService {

    /**
     * 查单个
     * @param id
     * @return
     */
    Travel get(String id);

    /**
     * 分页
     * @param qo
     * @return
     */
    Page<Travel> query(TravelQuery qo);

    /**
     * 添加与更新
     * @param travel
     */
    String saveOrUpdate(Travel travel);

   
    /**
     * 删除
     * @param id
     */
    void delete(String id);


    /**
     * 状态变动
     * @param id
     * @param state
     */
    void changeState(String id, int state);

    /**
     * 指定目的地下, 游记点击量前3
     * @param destId
     * @return
     */
    List<Travel> getViewnumTop3(String destId);

    /**
     * 查多个
     * @return
     */
    List<Travel> list();

    List<Travel> findByDestName(String destName);
}
