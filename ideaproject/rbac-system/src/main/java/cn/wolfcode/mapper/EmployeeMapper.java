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
    int queryForCount(QueryObject qo);
    List<Employee> queryForList(QueryObject qo);
    void insertRelation(@Param("eid") Long eid,@Param("rid") Long rid);
    void deleteRelation(Long id);
    Employee selectByusernameAndPassword(@Param("name") String username, @Param("password") String password);
    void updatePwd(@Param("password") String password,@Param("id") Long id);
}