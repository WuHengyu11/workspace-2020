package cn.wolfcode.service;

import cn.wolfcode.domain.Department;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;

import java.util.List;

public interface IDepartmentService {
    void save(Department dept);
    void delete(Long id);
    void update(Department dept);
    Department get(Long id);
    List<Department> listAll();
    PageResult<Department> query(QueryObject qo);
}
