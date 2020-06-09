package cn.wolfcode.luowowo.search.service;

import cn.wolfcode.luowowo.search.domain.DestinationEs;

public interface IDestinationEsService {

    /**
     * 保存
     */
    void save(DestinationEs entity);

    /**
     * 通过名字查询对象
     * @param name
     * @return
     */
    DestinationEs getByName(String name);
}
