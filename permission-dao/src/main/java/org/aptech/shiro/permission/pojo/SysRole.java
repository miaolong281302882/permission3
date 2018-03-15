package org.aptech.shiro.permission.pojo;
/**
 * 角色实体类
 * @author Miao  Long
 *
 */

import java.util.List;

import javax.annotation.Resource;

public class SysRole {
	@Resource
	private int id;//自增id
	
	private String name;//角色名称
	
	private int available;//是否可用
	
	private List<SysUser> users;//用户列表

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public List<SysUser> getUsers() {
		return users;
	}

	public void setUsers(List<SysUser> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "SysRole [id=" + id + ", name=" + name + ", avaliable=" + available + ", users=" + users + "]";
	}
	
	
}
