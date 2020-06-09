package cn.wolfcode.dept.mapper;

import cn.wolfcode.dept.domain.Department;

import java.util.List;

public interface DepartmentMapper {
    void insert(Department d);
    void delete(Long id);
    void update(Department d);
    Department get(Long id);
    List<Department> listAll();
}
