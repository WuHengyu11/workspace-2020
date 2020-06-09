package cn.wolfcode.mapper;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    int selectForCount(QueryObject qo);

    List<Employee> selectForList(QueryObject qo);

    void insertRelation(@Param("employee_id") Long employee_id, @Param("role_id") Long role_id);

    void insertRelation2(@Param("employee_id") Long employee_id, @Param("role_id") Long[] role_id);

    void deleteRelation(Long employee_id);

    Employee selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}