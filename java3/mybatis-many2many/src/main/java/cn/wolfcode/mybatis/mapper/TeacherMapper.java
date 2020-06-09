package cn.wolfcode.mybatis.mapper;

import cn.wolfcode.mybatis.domain.Teacher;

public interface TeacherMapper {
	void save(Teacher teacher);
	Teacher get(Long id);
}
