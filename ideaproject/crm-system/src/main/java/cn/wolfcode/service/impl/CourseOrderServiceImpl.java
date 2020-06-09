package cn.wolfcode.service.impl;

import cn.wolfcode.domain.CourseOrder;
import cn.wolfcode.mapper.CourseOrderMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.ICourseOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseOrderServiceImpl implements ICourseOrderService {

    @Autowired
    private CourseOrderMapper courseOrderMapper;

    @Override
    public void save(CourseOrder courseOrder) {
        courseOrderMapper.insert(courseOrder);
    }

    @Override
    public void update(CourseOrder courseOrder) {
        courseOrderMapper.updateByPrimaryKey(courseOrder);
    }

    @Override
    public CourseOrder get(Long id) {
        return courseOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CourseOrder> listAll() {
        return courseOrderMapper.selectAll();
    }

    @Override
    public PageInfo<CourseOrder> query(QueryObject qo) {
        //告诉分页插件,当前页以及每页显示的数量
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<CourseOrder> courseOrders = courseOrderMapper.queryForList(qo);
        //返回时用pageinfo封装
        return new PageInfo<CourseOrder>(courseOrders);
    }
}
