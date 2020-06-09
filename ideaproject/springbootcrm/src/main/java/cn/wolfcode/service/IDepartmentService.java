package cn.wolfcode.service;

import cn.wolfcode.domain.Department;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IDepartmentService {
    void save(Department department);
    void delete(Long id);
    void update(Department department);
    Department get(Long id);
    List<Department> listAll();
    PageInfo<Department> query(QueryObject qo);
}
