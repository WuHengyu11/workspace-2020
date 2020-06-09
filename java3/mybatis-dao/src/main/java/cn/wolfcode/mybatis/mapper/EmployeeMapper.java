package cn.wolfcode.mybatis.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.wolfcode.mybatis.domain.Employee;


public interface EmployeeMapper {
	void save(Employee e);
	void update(Employee e);
	void delete(Long[] ids);
	Employee get(Long id);
	List<Employee> selectAll();
	List<Employee> queryByMinSalaryAndMaxSalary(@Param("minSalary") BigDecimal minSalary,@Param("maxSalary") BigDecimal maxSalary);
}
