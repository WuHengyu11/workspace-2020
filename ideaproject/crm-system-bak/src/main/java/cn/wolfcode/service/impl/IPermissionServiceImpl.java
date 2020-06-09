package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.Permission;
import cn.wolfcode.mapper.PermissionMapper;
import cn.wolfcode.qo.PageResult;
import cn.wolfcode.qo.QueryObject;
import cn.wolfcode.service.IPermissionService;
import cn.wolfcode.util.RequiredPermission;
import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import sun.swing.StringUIClientPropertyKey;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class IPermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void delete(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Permission get(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Permission> listAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public PageInfo<Permission> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Permission> permissions = permissionMapper.queryForList(qo);
        return new PageInfo<>(permissions);
    }
    @Autowired
    private ApplicationContext ctx;//Spring的上下文路径
    @Override
    public void reload() {
        //获取数据中的所有的权限表达式
        List<String> expressions = permissionMapper.selectAllExpression();
        //根据注解从人其中获取多个bean对象
        Map<String, Object> beans = ctx.getBeansWithAnnotation(Controller.class);
        for (Object controller : beans.values()) {
            //获取controller的字节码对象
            Class clazz = controller.getClass();
            //获取controller中的方法
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                //判断方法是否有注解
                RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
                if(annotation != null){
                    //有注解处理
                    String value = annotation.value();//权限名称
                    //获取权限表达式方式一
                    String expression = annotation.expression();//权限表达式
                    //获取权限表达式方式二
                    //String simpleName = clazz.getSimpleName();// DepartmentController
                    //String simpleName = simpleName.replace("Controller", "");//Department
                    //simpleName = StringUtils.uncapitalize(simpleName);
                    //String methodName = method.getName();
                    //String expression = simpleName + ":" + methodName;
                    if (!expressions.contains(expression)){
                        //拿到权限注解中的属性,封装成权限对象
                        Permission permission = new Permission();
                        permission.setName(value);
                        permission.setExpression(expression);
                        //把权限保存到数据库中
                        permissionMapper.insert(permission);
                    }
                }
            }
        }
    }

    @Override
    public List<String> selectExpressionByEmpId(Long id) {
        return permissionMapper.selectExpressionByEmpId(id);
    }
}
