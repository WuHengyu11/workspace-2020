package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.Travel;
import cn.wolfcode.luowowo.query.TravelQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 游记的服务
 */
public interface ITravelService {
    /**
     * 游记的添加
     * @param travel
     */
    void save(Travel travel);

    /**
     * 游记的删除
     * @param id
     */
    void delete(String id);

    /**
     * 游记的更新
     * @param travel
     */
    void update(Travel travel);

    /**
     * 游记的获取(单个)
     */
    Travel get(String id);

    /**
     * 游记的获取(多个)
     * @return
     */
    List<Travel> listAll();

    /**
     * 游记的分页查询
     */
    Page query(TravelQuery qo);

    /**
     * 游记的增加或更新
     * @param travel
     */
    void saveOrUpdate(Travel travel);

    /**
     * 查询指定目的地下的游记前3
     * @param destId
     * @return
     */
    List<Travel> queryViewnumTop3(String destId);

    /**
     * 修改状态
     * @param travel
     */
    void changeState(Travel travel);

    /**
     * 根据指定id查询游记内容
     * @param id
     * @return
     */
    String getContentById(String id);
}
