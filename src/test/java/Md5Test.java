import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class Md5Test {
    @Test
    public void testMd5() {
        //做Md5加密,第一个参数是要加密的密码,第二个参数是盐,第三个参数是散列次数(加密次数)
        Md5Hash hash = new Md5Hash("666","wolf",2);
        System.out.println(hash);

        SimpleHash simpleHash = new SimpleHash("sha","666","wolf",2);
        System.out.println(simpleHash);
    }
}
