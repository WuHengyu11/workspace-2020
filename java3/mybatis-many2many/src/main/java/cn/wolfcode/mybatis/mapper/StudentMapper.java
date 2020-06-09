package cn.wolfcode.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import cn.wolfcode.mybatis.domain.Student;

public interface StudentMapper {
	void save(Student s);
	void insertRelationWithTeacher(@Param("studentId")Long studentId,@Param("teacherId")Long teacherId);
	Student get(Long id);
	void delete(Long id);
	void deleteRelation(Long studentId);
}
