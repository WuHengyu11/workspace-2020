package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Salary;
import cn.wolfcode.mapper.SalaryMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.ISalaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryServiceImpl implements ISalaryService {
    //关联mapper
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
    public List<Salary> listAll() {
        return salaryMapper.selectAll();
    }

    @Override
    public PageInfo<Salary> query(QueryObject qo) {
        //不需要手动执行count,由分页插件执行
        //告诉分页插件,当前页以及每页显示的数量,分页插件会自动算出start的值放到limit
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());//对下一句sql进行自动分页
        List<Salary> salarys = salaryMapper.queryForList(qo);
        //返回时使用pageinfo进行封装
        return new PageInfo<Salary>(salarys);
    }
}
