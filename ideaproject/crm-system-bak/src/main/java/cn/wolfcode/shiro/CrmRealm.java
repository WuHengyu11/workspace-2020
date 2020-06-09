package cn.wolfcode.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CrmRealm extends AuthorizingRealm {
    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //准备假数据
        String username = "admin";
        String password = "1";
        //判断token里面的用户名是否存在,是否与username相同,getPrincipal获取身份(用户名)
        if(username.equals(token.getPrincipal())){
            //用户名,密码,当前数据源的名字(标记)
            return new SimpleAuthenticationInfo(username,password,"crmRealm");//如果返回的不为空(带有正确的密码),shiro就会自己去做密码功能
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
        return null;
    }
}
