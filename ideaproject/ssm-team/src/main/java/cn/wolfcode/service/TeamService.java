package cn.wolfcode.service;

import cn.wolfcode.domain.Team;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;

import java.util.List;

public interface TeamService {
    /**
     * 向team表插入数据
     * @param t
     */
    void save(Team t);

    /**
     * 删除team表的指定数据
     * @param id
     */
    void delete(Long id);

    /**
     * 更新team表的指定数据
     * @param t
     */
    void update(Team t);

    /**
     * 查询team表的指定数据
     * @param id
     * @return
     */
    Team get(Long id);

    /**
     * 查询team表的所有数据
     * @return
     */
    List<Team> listAll();

    PageResult<Team> query(QueryObject qo);
}
