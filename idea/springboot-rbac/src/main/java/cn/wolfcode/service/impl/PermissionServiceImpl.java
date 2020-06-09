package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.Permission;
import cn.wolfcode.mapper.PermissionMapper;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IPermissionService;
import cn.wolfcode.util.RequiredPermission;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    @Transactional
    public void delete(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public Permission selectOne(Long id) {
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        System.out.println(permission);
        return permission;
    }
    @Override
    @Transactional
    public List<Permission> selectAll() {
        List<Permission> permissions = permissionMapper.selectAll();
        System.out.println(permissions);
        return permissions;
    }
    @Override
    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Permission> list = permissionMapper.selectForList(qo);
        return new PageInfo(list);
    }

    @Autowired
    private ApplicationContext ctx;  //spring上下文对象

    @Override
    public void reload() {
        //获取数据库中所有的表达式
        List<String> expressions = permissionMapper.selectAllExpressions();
        //拿到controller的注解的bean对象
        Map<String, Object> beans = ctx.getBeansWithAnnotation(Controller.class);
        //System.out.println(beans);
        for (Object controller : beans.values()) {
            //获取controller 及所有方法
            Class clazz = controller.getClass();
            //获取方法
            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {
                RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
                //判断是否有加上权限注解
                if(annotation != null){
                    String expression = annotation.expression();
                    //去重处理,判断表达式是否存在,不存在便插入
                    if(!expressions.contains(expression)){
                        String name = annotation.value();
                        //将注解的属性封装   存入数据库中
                        Permission permission = new Permission();
                        permission.setName(name);
                        permission.setExpression(expression);
                        permissionMapper.insert(permission);
                    }

                }
            }
        }



    }

    @Override
    public List<String> selectExpressionByEmpid(Long id) {
        return permissionMapper.selectExpressionByEmpid(id);
    }
}
