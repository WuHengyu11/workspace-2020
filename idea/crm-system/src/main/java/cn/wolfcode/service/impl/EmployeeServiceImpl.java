package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.mapper.EmployeeMapper;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IEmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    //关联mapper
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public void save(Employee employee,Long[] ids) {
        //对密码加密
        Md5Hash md5Hash = new Md5Hash(employee.getPassword(), employee.getName());
        employee.setPassword(md5Hash.toString());
        employeeMapper.insert(employee);
        if(ids != null && ids.length > 0){
            for(Long rid : ids){
                employeeMapper.insertRelation(employee.getId(), rid);
            }
        }
    }

    @Override
    public void delete(Long id) {
        employeeMapper.deleteByPrimaryKey(id);
        //删除关联关系
        employeeMapper.deleteRelation(id);
    }

    @Override
    public void update(Employee employee,Long[] ids) {
        employeeMapper.updateByPrimaryKey(employee);
        //删除关联关系
        employeeMapper.deleteRelation(employee.getId());
        //重新关联关系
        if(ids != null && ids.length > 0){
            for(Long rid : ids){
                employeeMapper.insertRelation(employee.getId(), rid);
            }
        }
    }

    @Override
    public Employee get(Long id) {

        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> listAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public PageInfo<Employee> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Employee> employees = employeeMapper.queryForList(qo);
        return new PageInfo<>(employees);
    }

    @Override
    public Employee login(String username, String password) {
        //根据用户名username查询数据库
        Employee employee = employeeMapper.selectByusernameAndPassword(username,password);
        if (employee==null){
            throw new RuntimeException("账号和密码不匹配");
        }
        return employee;
    }

    @Override
    public void updatePwd(String password, Long id) {
        employeeMapper.updatePwd(password,id);
    }

    @Override
    public void batchDelete(Long[] ids) {
        employeeMapper.batchDelete(ids);
    }

    @Override
    public Workbook exportXls() {
        //查询所有员工数据
        List<Employee> employees = employeeMapper.selectAll();
        //创建工作簿excel
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建一张表
        Sheet sheet = wb.createSheet("员工通讯录");
        //创建标题行
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("邮箱");
        row.createCell(2).setCellValue("年龄");
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            //创建一行(行的索引)
            row = sheet.createRow(i+1);
            //创建单元格(列的索引)并写入内容
            row.createCell(0).setCellValue(employee.getName());
            row.createCell(1).setCellValue(employee.getEmail());
            row.createCell(2).setCellValue(employee.getAge());
        }
        return wb;
    }

    @Override
    public void importXls(MultipartFile file) throws IOException {
        //读excel文件
        HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());
        //读一个表
        Sheet sheet = wb.getSheetAt(0);
        //获取最后一行的索引值
        int lastRowNum = sheet.getLastRowNum();
        Employee employee = new Employee();
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);//每一行就是一个员工
            String name = row.getCell(0).getStringCellValue();
            double email = row.getCell(1).getNumericCellValue();
            employee.setName(name);
            employee.setEmail(String.valueOf(email));
            CellType cellType = row.getCell(2).getCellType();
            if (cellType.equals(CellType.STRING)){
                String age = row.getCell(2).getStringCellValue();
                employee.setAge(Integer.valueOf(age));
            }else{
                double age = row.getCell(2).getNumericCellValue();
                employee.setAge((int)age);
            }
            //设置默认密码
            employee.setPassword("1");
            //保存到数据库
            //this.save(employee,null);
            employeeMapper.insert(employee);
        }
    }

    @Override
    public List<Employee> selectByRoleSn(String... sns) {
        return employeeMapper.selectByRoleSn(sns);
    }
}
