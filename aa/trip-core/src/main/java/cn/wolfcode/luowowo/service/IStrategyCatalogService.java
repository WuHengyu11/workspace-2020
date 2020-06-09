package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.StrategyCatalog;
import cn.wolfcode.luowowo.query.StrategyCatalogQuery;
import cn.wolfcode.luowowo.vo.CatalogVO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 攻略分类服务接口
 */
public interface IStrategyCatalogService {

    /**
     * 查单个
     * @param id
     * @return
     */
    StrategyCatalog get(String id);

    /**
     * 分页
     * @param qo
     * @return
     */
    Page<StrategyCatalog> query(StrategyCatalogQuery qo);

    /**
     * 添加与更新
     * @param strategyCatalog
     */
    void saveOrUpdate(StrategyCatalog strategyCatalog);


    /**
     * 删除
     * @param id
     */
    void delete(String id);

    /**
     * 攻略分类
     * @return
     */
    List<CatalogVO> groupList();

    /**
     * 通过目的地id查询攻略分类
     * @param destId
     * @return
     */
    List<StrategyCatalog> queryCatalogByDestId(String destId);
}
