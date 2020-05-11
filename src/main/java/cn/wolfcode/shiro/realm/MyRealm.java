package cn.wolfcode.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealm extends AuthorizingRealm {
    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //读取数据库,把token中的username取出来
        Object principal = token.getPrincipal();
        //然后读取数据库,查询是否有该username
        String username = "zhangsan";
        String password = "3df48e7d62482542554413b792d0d399";

        //如果用户名不存在,就直接return null就可以了
        if (!username.equals(principal)) {
            return null;
        }
        //返回认证对象,包括用户名,正确的凭证,需要传入当前数据源的名字
        //由上一层来做相关的凭证匹配操作
       return new SimpleAuthenticationInfo(username,password, ByteSource.Util.bytes("wolf"),"MyRealm");
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

}
