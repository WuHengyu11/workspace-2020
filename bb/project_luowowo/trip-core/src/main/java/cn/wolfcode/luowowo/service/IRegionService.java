package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.Region;
import cn.wolfcode.luowowo.query.RegionQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 区域的服务
 */
public interface IRegionService {
    /**
     * 区域的添加
     * @param region
     */
    void save(Region region);

    /**
     * 区域的删除
     * @param id
     */
    void delete(String id);

    /**
     * 区域的更新
     * @param region
     */
    void update(Region region);

    /**
     * 区域的获取(单个)
     */
    Region get(String id);

    /**
     * 区域的获取(多个)
     * @return
     */
    List<Region> listAll();

    /**
     * 区域的分页查询
     */
    Page query(RegionQuery qo);

    /**
     * 区域的增加或更新
     * @param region
     */
    void saveOrUpdate(Region region);

    /**
     * 修改热门状态
     * @param hot
     * @param id
     */
    void changeHotValue(int hot, String id);

    /**
     * 查询指定区域id关系目的地集合
     * @param rid
     * @return
     */
    List<Destination> getDestByRegionId(String rid);

    /**
     *查询热门区域的集合
     * @return
     */
    List<Region> queryHotRegions();
}
