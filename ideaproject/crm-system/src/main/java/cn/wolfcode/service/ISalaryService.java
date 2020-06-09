package cn.wolfcode.service;

import cn.wolfcode.domain.Salary;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ISalaryService {
    void save(Salary salary);
    void delete(Long id);
    void update(Salary salary);
    Salary get(Long id);
    List<Salary> listAll();
    PageInfo<Salary> query(QueryObject qo);
}
