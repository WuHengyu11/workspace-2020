package cn.wolfcode.service.impl;

import cn.wolfcode.mapper.CustomerReportMapper;
import cn.wolfcode.qo.CustomerReportQuery;
import cn.wolfcode.service.ICustomerReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerReportServiceImpl implements ICustomerReportService {
    @Autowired
    private CustomerReportMapper customerReportMapper;

    @Override
    public PageInfo selectCustomerReport(CustomerReportQuery qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<HashMap> list = customerReportMapper.selectCustomerReport(qo);
        return new PageInfo(list);
    }

    @Override
    public List<HashMap> listAll(CustomerReportQuery qo) {
        return customerReportMapper.selectCustomerReport(qo);
    }
}
