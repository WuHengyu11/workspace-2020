package cn.wolfcode.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentServiceImplTest {
    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void save() {
        Department department = new Department();
        department.setName("技术部");
        department.setSn("JS");
        departmentService.save(department);
    }

    @Test
    public void delete() {
        departmentService.delete(2L);
    }

    @Test
    public void update() {
        Department department = new Department();
        department.setId(3L);
        department.setName("技术部1");
        department.setSn("JS1");
        departmentService.update(department);
    }

    @Test
    public void get() {
        Department department = departmentService.get(1L);
        System.out.println(department);
    }

    @Test
    public void listAll() {
        List<Department> departments = departmentService.listAll();
        for (Department department : departments) {
            System.out.println(department);
        }
    }
}