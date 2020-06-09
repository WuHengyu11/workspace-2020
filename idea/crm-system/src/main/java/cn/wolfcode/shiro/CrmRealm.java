package cn.wolfcode.shiro;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.mapper.EmployeeMapper;
import cn.wolfcode.mapper.PermissionMapper;
import cn.wolfcode.mapper.RoleMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("crmRealm")
public class CrmRealm extends AuthorizingRealm {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //判断token里面的用户名是否存在,是否与username相同,getPrincipal获取身份(用户名)
        Employee employee = employeeMapper.selectByName((String) token.getPrincipal());
        if(employee != null){
            //员工对象(自动绑定到subject),密码,当前数据源的名字(标记)
            return new SimpleAuthenticationInfo(employee,employee.getPassword(),"crmRealm");//如果返回的不为空(带有正确的密码),shiro就会自己去做密码功能
        }
        return null;//如果返回null,shiro会抛出没有账号的异常
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //该方法返回的结果中包含了哪些角色和权限数据,那么当前的subject主体就拥有哪些角色和权限数据
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登录用户的id
        /*方式一
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
         */
        //方式二
        //shiro知道在查询用户权限信息的时候,需要拿到身份信息,形参上就直接注入进来了
        Employee employee = (Employee) principals.getPrimaryPrincipal();
        if (employee.isAdmin()){//是管理员
            info.addStringPermission("*:*");//拥有所有权限
            info.addRole("Admin");//管理员角色
        }else{
            //根据员工id查询该员工拥有的权限
            List<String> permissions = permissionMapper.selectExpressionByEmpId(employee.getId());
            info.addStringPermissions(permissions);
            //根据员工id查询该员工拥有的角色
            List<String> roles = roleMapper.selectSnByEmpId(employee.getId());
            info.addRoles(roles);
        }
        return info;
    }
}
