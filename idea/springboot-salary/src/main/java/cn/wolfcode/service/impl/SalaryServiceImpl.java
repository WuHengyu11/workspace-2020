package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Salary;
import cn.wolfcode.mapper.EmployeeMapper;
import cn.wolfcode.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryServiceImpl implements ISalaryService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private Salary salary;
    @Override
    public void save(Salary salary) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Salary salary) {

    }

    @Override
    public Salary get(Long id) {
        return null;
    }

    @Override
    public List<Salary> listAll() {
        return null;
    }
}
