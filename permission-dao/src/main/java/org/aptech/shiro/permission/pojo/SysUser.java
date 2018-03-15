package org.aptech.shiro.permission.pojo;
/**
 * 用户实体类
 * @author Miao  Long
 *
 */

import java.util.List;
 
public class SysUser {
	private int  id ;//自增编号
	
	private String username;//账号
	
	private String password;//密码
	
	private String salt;//盐值
	
	private int locked;//是否锁定
	
	private List<SysRole> roles;//角色列表
	
	private List<SysPermission> menus;
	 
	

	public List<SysPermission> getMenus() {
		return menus;
	}

	public void setMenus(List<SysPermission> menus) {
		this.menus = menus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "SysUser [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt
				+ ", locked=" + locked + ", roles=" + roles + "]";
	}
	
	
	
}
