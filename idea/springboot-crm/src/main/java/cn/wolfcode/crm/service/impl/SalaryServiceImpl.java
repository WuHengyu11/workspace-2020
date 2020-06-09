package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISalaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SalaryServiceImpl implements ISalaryService {
    @Autowired
    private SalaryMapper salaryMapper;

    @Override
    public void save(Salary salary) {
        salaryMapper.insert(salary);
    }

    @Override
    public void delete(Long id) {
        salaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Salary salary) {
        salaryMapper.updateByPrimaryKey(salary);
    }

    @Override
    public Salary get(Long id) {
        return salaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Salary> selectAll() {
        return salaryMapper.selectAll();
    }


    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Salary> list = salaryMapper.selectForList(qo);
        return new PageInfo(list);
    }
}
