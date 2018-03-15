package org.test.demo01;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
/**
 * 自定义realm
 * @author Miao  Long
 *
 */
public class CustomRealm extends AuthorizingRealm {

	 
	/**
	 * 授权方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0){
		
		return null;
	}

	/**
	 * 认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		
		System.out.println("开始认证！");
		//如果账号不正确return null shiro会自动处理 
		String principal = (String) token.getPrincipal();
		if (!principal.equals("zhangsan")) {
			return null;
		} 
		//如果账号正确返回实体类 验证密码是否正确
		return new SimpleAuthenticationInfo(principal, "0659c7992e268962384eb17fafe88364", ByteSource.Util.bytes("abc"), this.getName());
	}

}
