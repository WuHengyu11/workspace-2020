import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

public class ShiroTest {
    @Test
    public void testLogin() throws Exception{
        //提供假数据来体验shiro
        //构造shiro的环境(安全管理器)
        //加载shiro.ini配置文件,得到配置中的用户信息
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //创建shiro的安全管理器
        SecurityManager manager = factory.getInstance();
        //将创建的安全管理器添加到运行环境中
        SecurityUtils.setSecurityManager(manager);
        //获取当前的主体(用户)
        Subject subject = SecurityUtils.getSubject();//无论现在有没有登录都可以拿到该对象,但是需要利用isAuthenticated方法得到认证的状态
        //isAuthenticated:是否认证
        System.out.println("认证状态:" + subject.isAuthenticated());
        //创建登录的令牌
        UsernamePasswordToken token = new UsernamePasswordToken("lisi", "666");
        //执行登录
        subject.login(token);
        System.out.println("认证状态:" + subject.isAuthenticated());

        //判断用户是否有role1角色
        System.out.println("判断用户是否有role1角色" + subject.hasRole("role1"));
        System.out.println("判断用户是否有role2角色" + subject.hasRole("role2"));
        System.out.println(subject.hasAllRoles(Arrays.asList("role1", "role2")));
        //抛异常的方式
        //subject.checkRole("role1");
        subject.checkRole("role2");

        //判断用户是否有某个权限
        System.out.println(subject.isPermitted("user:update"));
        System.out.println(subject.isPermitted("user:delete"));
        //抛异常的方式
        subject.checkPermission("user:delete");

        //注销
        subject.logout();
        System.out.println("认证状态:" + subject.isAuthenticated());
    }
}
