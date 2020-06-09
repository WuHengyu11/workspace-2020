package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.Destination;
import cn.wolfcode.luowowo.domain.UserInfo;
import cn.wolfcode.luowowo.query.DestinationQuery;
import org.springframework.data.domain.Page;
import sun.security.krb5.internal.crypto.Des;

import java.util.List;

/**
 * 目的地的服务
 */
public interface IDestinationService {
    /**
     * 目的地的保存
     */
    void save(Destination destination);

    /**
     * 目的地的删除
     * @param id
     */
    void delete(String id);

    /**
     * 目的地的删除
     * @param destination
     */
    void update(Destination destination);

    /**
     * 目的地的获取(单个)
     * @param id
     * @return
     */
    Destination get(String id);

    /**
     * 目的地的获取(多个)
     * @return
     */
    List<Destination> listAll();

    /**
     * 通过id集合查询目的地集合
     * @param refIds
     * @return
     */
    List<Destination> queryByIds(List<String> refIds);

    /**
     * 目的地的分页查询
     * @param qo
     * @return
     */
    Page query(DestinationQuery qo);

    /**
     * 查询吐司
     * @param parentId
     * @return
     */
    List<Destination> getToasts(String parentId);

    /**
     *通过区域id查询目的地
     * @param regionId
     * @return
     */
    List<Destination> queryByRegionId(String regionId);
}
