import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

public class LoginTest {
    @Test
    public void testLogin() {
        //创建安全管理器的工厂对象
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //获取安全管理器
        SecurityManager securityManager = factory.getInstance();
        //设置到当前的环境当中
        SecurityUtils.setSecurityManager(securityManager);
        //获取subject
        Subject subject = SecurityUtils.getSubject();
        //创建登录令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        //执行认证操作
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        }

        //认证状态
        //System.out.println(subject.isAuthenticated());

        //权限判断
        //check的方式是没有返回结果,如果没有权限就抛出异常
        //subject.checkPermission("user:create");
        //subject.checkPermission("user:update");

        //isPermitted有boolean类型的返回值
        System.out.println(subject.isPermitted("user:create"));
        System.out.println(subject.isPermitted("user:update"));
        //把所有结果都放到一个数组中
        System.out.println(Arrays.toString(subject.isPermitted("user:create", "user:update")));
        //是否包含指定的所有权限,只要其它一个没有,都为false
        System.out.println(subject.isPermittedAll("user:create","user:delete"));

        //角色判断
        //subject.checkRole("role1");
        //subject.checkRole("role2");
        System.out.println("==================");
        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.hasRole("role2"));

        //注销
        //subject.logout();
        //System.out.println(subject.isAuthenticated());
    }
}
