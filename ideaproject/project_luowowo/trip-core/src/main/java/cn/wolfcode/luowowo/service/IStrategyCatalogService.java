package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.StrategyCatalog;
import cn.wolfcode.luowowo.query.StrategyCatalogQuery;
import cn.wolfcode.luowowo.vo.CatalogVO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 攻略分类的服务
 */
public interface IStrategyCatalogService {
    /**
     * 攻略分类的添加
     * @param strategyCatalog
     */
    void save(StrategyCatalog strategyCatalog);

    /**
     * 攻略分类的删除
     * @param id
     */
    void delete(String id);

    /**
     * 攻略分类的更新
     * @param strategyCatalog
     */
    void update(StrategyCatalog strategyCatalog);

    /**
     * 攻略分类的获取(单个)
     */
    StrategyCatalog get(String id);

    /**
     * 攻略分类的获取(多个)
     * @return
     */
    List<StrategyCatalog> listAll();

    /**
     * 攻略分类的分页查询
     */
    Page query(StrategyCatalogQuery qo);

    /**
     * 攻略的增加或更新
     * @param strategyCatalog
     */
    void saveOrUpdate(StrategyCatalog strategyCatalog);

    /**
     * 分组下拉框
     * @return
     */
    List<CatalogVO> groupList();

    /**
     * 通过目的地id查询该目的地下所有的分类
     * @param destId
     * @return
     */
    List<StrategyCatalog> queryCatalogByDestId(String destId);
}
