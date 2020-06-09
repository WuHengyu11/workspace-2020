package cn.wolfcode.crm.service;

import cn.wolfcode.crm.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ISalaryService {
    void save(Salary salary);
    void delete (Long id);
    void update(Salary salary);
    Salary get(Long id );
    List<Salary>selectAll();

    PageInfo query(QueryObject qo);
}
