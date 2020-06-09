package cn.wolfcode.service;

import cn.wolfcode.domain.SystemDictionaryItem;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ISystemDictionaryItemService {

    void save(SystemDictionaryItem systemDictionaryItem);

    void delete(Long id);

    void update(SystemDictionaryItem systemDictionaryItem);

    SystemDictionaryItem selectOne(Long id);

    List<SystemDictionaryItem> selectAll();

    PageInfo<SystemDictionaryItem> query(QueryObject qo);

    List<SystemDictionaryItem> selectByDicsn(String sn);

    List<SystemDictionaryItem> selectByParentId();
}
