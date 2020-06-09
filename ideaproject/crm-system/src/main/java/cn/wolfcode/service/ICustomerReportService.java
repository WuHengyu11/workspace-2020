package cn.wolfcode.service;

import cn.wolfcode.qo.CustomerReportQuery;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ICustomerReportService {
    PageInfo selectCustomerReport(CustomerReportQuery qo);
    List<HashMap> listAll(CustomerReportQuery qo);
}
