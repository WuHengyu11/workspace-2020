package cn.wolfcode.crm.service;

import cn.wolfcode.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ISystemDictionaryService {
    void delete(Long id);
    void save(SystemDictionary systemDictionary);
    SystemDictionary get(Long id);
    List<SystemDictionary> listAll();
    void update(SystemDictionary systemDictionary);

    /**
     * 分页
     * @param qo
     * @return
     */
    PageInfo query(QueryObject qo);
}
