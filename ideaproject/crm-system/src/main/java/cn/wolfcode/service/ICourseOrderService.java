package cn.wolfcode.service;

import cn.wolfcode.domain.CourseOrder;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICourseOrderService {
    void save(CourseOrder courseOrder);
    void update(CourseOrder courseOrder);
    CourseOrder get(Long id);
    List<CourseOrder> listAll();
    // 分页查询的方法
    PageInfo<CourseOrder> query(QueryObject qo);
}
