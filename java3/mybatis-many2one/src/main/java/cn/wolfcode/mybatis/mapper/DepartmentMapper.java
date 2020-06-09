package cn.wolfcode.mybatis.mapper;

import cn.wolfcode.mybatis.domain.Department;

public interface DepartmentMapper {
	void save(Department dept);
	Department get(Long id);
}
