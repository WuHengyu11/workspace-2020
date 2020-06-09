package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.query.DestinationQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 目的地服务接口
 */
public interface IDestinationService {

    /**
     * 查单个
     * @param id
     * @return
     */
    Destination get(String id);

    /**
     * 分页
     * @param qo
     * @return
     */
    Page<Destination> query(DestinationQuery qo);

    /**
     * 查询所有
     * @return
     */
    List<Destination> list();

    /**
     * 通过区域id查询关联的目的地集合
     * @param rid
     * @return
     */
    List<Destination> getDestByRegionId(String rid);


    /**
     * 查询吐司
     * @param parentId
     * @return
     */
    List<Destination> getToasts(String parentId);
    /**
     * 通过区域id查询关联的目的地集合
     * @param regionId
     * @return
     */
    List<Destination> getDestByRegionIdForApi(String regionId);

    Destination getByName(String keyword);
}
