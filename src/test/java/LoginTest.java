import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

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
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111");
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
        System.out.println(subject.isAuthenticated());
        //注销
        subject.logout();
        System.out.println(subject.isAuthenticated());
    }
}
