package cn.wolfcode.mapper;

import cn.wolfcode.domain.Salary;
import java.util.List;

public interface SalaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Salary record);

    Salary selectByPrimaryKey(Long id);

    List<Salary> selectAll();

    int updateByPrimaryKey(Salary record);
}