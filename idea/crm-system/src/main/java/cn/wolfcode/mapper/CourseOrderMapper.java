package cn.wolfcode.mapper;

import cn.wolfcode.domain.CourseOrder;
import cn.wolfcode.qo.QueryObject;

import java.util.List;

public interface CourseOrderMapper {
    int deleteByPrimaryKey(Long id);
    int insert(CourseOrder record);
    CourseOrder selectByPrimaryKey(Long id);
    List<CourseOrder> selectAll();
    int updateByPrimaryKey(CourseOrder record);
    List<CourseOrder> queryForList(QueryObject qo);
}