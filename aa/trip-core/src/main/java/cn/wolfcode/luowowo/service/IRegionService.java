package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.Region;
import cn.wolfcode.luowowo.query.RegionQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 区域服务接口
 */
public interface IRegionService {

    /**
     * 查单个
     * @param id
     * @return
     */
    Region get(String id);

    /**
     * 分页
     * @param qo
     * @return
     */
    Page<Region> query(RegionQuery qo);

    /**
     * 添加与更新
     * @param region
     */
    void saveOrUpdate(Region region);

    /**
     * 修改热门状态
     * @param id
     * @param hot
     */
    void changeHotValue(String id, int hot);

    /**
     * 删除
     * @param id
     */
    void delete(String id);

    /**
     * 查询热门的区域集合
     * @return
     */
    List<Region> queryHotRegion();
}
