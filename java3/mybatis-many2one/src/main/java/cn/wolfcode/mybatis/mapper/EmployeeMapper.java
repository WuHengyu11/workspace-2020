package cn.wolfcode.mybatis.mapper;

import cn.wolfcode.mybatis.domain.Employee;

public interface EmployeeMapper {
	void save(Employee e);
	Employee get(Long id);
}
