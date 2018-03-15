
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class test {

	public static void main(String[] args) {
		 
		System.out.println(new Md5Hash("123456","abc").toString());
		System.out.println(new Md5Hash("123456","abc",1).toString());
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro2.ini");
		
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("lisi", "456");
		
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			System.out.println("登录失败！");
			e.printStackTrace();
		}
		
		System.out.println("张三是否有用户创建权限:" + subject.isPermitted("user:delete"));
		System.out.println(subject.isAuthenticated());
		
		 
	}
}
