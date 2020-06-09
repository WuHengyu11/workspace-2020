package cn.wolfcode.mapper;

import cn.wolfcode.domain.Department;
import cn.wolfcode.qo.QueryObject;

import java.util.List;

public interface DepartmentMapper {

    void save(Department dep);

    void delete(Long id);

    void update(Department dep);

    Department selectOne(Long id);

    List<Department> selectAll();

    int selectForCount(QueryObject qo);


    List<Department> selectForList(QueryObject qo);
}
