package cn.wolfcode.service;

import cn.wolfcode.domain.Department;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IDepartmentService {

    void save(Department dep);

    void delete(Long id);

    void update(Department dep);

    Department selectOne(Long id);

    List<Department> selectAll();

    PageInfo query(QueryObject qo);

}
