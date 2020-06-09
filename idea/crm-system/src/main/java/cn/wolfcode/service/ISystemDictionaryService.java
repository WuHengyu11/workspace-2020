package cn.wolfcode.service;

import cn.wolfcode.domain.SystemDictionary;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ISystemDictionaryService {

    void save(SystemDictionary systemDictionary);

    void delete(Long id);

    void update(SystemDictionary systemDictionary);

    SystemDictionary selectOne(Long id);

    List<SystemDictionary> selectAll();

    PageInfo<SystemDictionary> query(QueryObject qo);

}
