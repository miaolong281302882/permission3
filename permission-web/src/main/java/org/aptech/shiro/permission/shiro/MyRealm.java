package org.aptech.shiro.permission.shiro;

import java.util.ArrayList;
import java.util.List; 
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.aptech.shiro.permission.dao.SysPermissionDao;
import org.aptech.shiro.permission.dao.SysUserDao;
import org.aptech.shiro.permission.pojo.SysPermission;
import org.aptech.shiro.permission.pojo.SysUser;
/**
 * 自定义realm
 * @author Miao  Long
 *
 */
public class MyRealm extends AuthorizingRealm {

	private SysUserDao sysUserDao;
	
	private SysPermissionDao sysPermissionDao;
	
	
	 

	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	public void setSysPermissionDao(SysPermissionDao sysPermissionDao) {
		this.sysPermissionDao = sysPermissionDao;
	}

	/**
	 * 授权方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		System.out.println("开始授权认证了");
		//取出登陆的用户设置权限
		SysUser user = (SysUser) principal.getPrimaryPrincipal();
		
		List<SysPermission> list = sysPermissionDao.getPersByUserId(user.getId());
		List<String> perList = new ArrayList<>();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		for (SysPermission sysPermission : list) {
			if (sysPermission.getPercode() != null) {
				perList.add(sysPermission.getPercode());
			}
			System.out.println(sysPermission.getPercode());
		}
		info.addStringPermissions(perList); 
		return info;
	}

	/**
	 * 认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("开始认证了！");
	    String principal = 	(String) token.getPrincipal();
	    System.out.println(principal);
	    SysUser user = new SysUser();
	    user.setUsername(principal);
	    SysUser login_user = sysUserDao.selectByName(user);
	    System.out.println(login_user);
	    if (login_user == null) {
	    	return null;
		}
	    List<SysPermission> perList = sysPermissionDao.getPersByUserId(login_user.getId());
	    
	    login_user.setMenus(perList); 
		return new SimpleAuthenticationInfo(login_user, login_user.getPassword(), ByteSource.Util.bytes(login_user.getSalt()), this.getName());
	}

}
