package cn.wolfcode.dept.test;

import cn.wolfcode.dept.domain.Department;
import cn.wolfcode.dept.mapper.DepartmentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentMapperTest {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Test
    public void insert() {
        Department d = new Department();
        d.setName("李四");
        d.setSn("ls");
        departmentMapper.insert(d);
    }

    @Test
    public void delete() {
        departmentMapper.delete(1L);
    }

    @Test
    public void update() {
        Department d = new Department();
        d.setId(2L);
        d.setName("张飞3");
        d.setSn("zf");
        departmentMapper.update(d);
    }

    @Test
    public void get() {
        Department d = departmentMapper.get(2L);
        System.out.println(d);
    }

    @Test
    public void listAll() {
        List<Department> departments = departmentMapper.listAll();
        for (Department department : departments) {
            System.out.println(department);
        }
    }
}