package org.permission.dao;


import java.util.List; 
import javax.annotation.Resource;

import org.aptech.shiro.permission.dao.SysPermissionDao;
import org.aptech.shiro.permission.dao.SysUserDao;
import org.aptech.shiro.permission.pojo.SysPermission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestUser {

	@Resource
	private SysUserDao sysUserDao;
	
	@Resource
	private SysPermissionDao sysPermissionDao;
	
	
	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
	
	
	@Test
	public void testGenerate()  {
		
		List<String> list = sysPermissionDao.getPermissionsByUserId(1);
		System.out.println(list.toString());
		for (String string : list) {
			System.out.println(string);
		}
		
//		SysUser user = new SysUser();
//		user.setUsername("123");
//		System.out.println(sysUserDao.selectByName(user));
		//List<SysPermission> list = sysPermissionDao.getPersByUserId(1);
//		for (SysPermission sysPermission : list) {
//			//System.out.println(sysPermission);
//		}
		//System.out.println(list.get(0).getChildren());
		//Class[] classes = {SysPermission.class};
//		ProjectUtils.setClasses(classes);
//		try {
//			ProjectUtils.gererateDaoInterface();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}  
//		SysPermission sysPermission = sysPermissionDao.getById(11);
//		sysPermission.setName("图书查询");
//		sysPermissionDao.update(sysPermission);
		
//		System.out.println();System.out.println();System.out.println();
//		List<SysPermission> list1 = sysPermissionDao.getListByCondition(0, 10, null, "id", "asc");
//		for (SysPermission sysPermission2 : list1) {
//			System.out.println(sysPermission2);
//		}
//		System.out.println();System.out.println();System.out.println();
	}
	
	
	
	@Test
	public void testSelect() {
//		 SysUser user = new SysUser();
//		 //user.setUsername("z");
//		 List<SysRole> list2 = new ArrayList<>();
//		 SysRole role = new SysRole();
//		 role.setId(1);
//		 list2.add(role);
//		 SysRole role2 = new SysRole();
//		 role2.setId(2);
//		 SysRole role3 = new SysRole();
//		 role3.setId(3);
//			list2.add(role2);
//			list2.add(role3);
//			user.setRoles(list2);
//		
//		
//		List<SysUser> list = sysUserDao.getListByCondition(0, 5, user, "id", "desc");
//		//System.out.println(list.size());
//		for (SysUser sysUser : list) {
//			System.out.println(sysUser.getUsername());
//			for (SysRole role1 : sysUser.getRoles()) {
//				System.out.println(role1.getName());
//			}
//			
//		}
	}

	@Test
	public void testAdd() {
//		for (int i = 0; i < 15; i++) {
//			SysUser user = new SysUser();
//			user.setUsername("miaolong-" + i);
//			user.setLocked(0);
//			user.setPassword("123456");
//			user.setSalt("ol-" + i);
//			sysUserDao.add(user);
//		}
	}
	
	@Test
	public void testBatchAdd() {
//		Integer [] ids = {10,11,12,13};
//		sysUserDao.deleteByIds(ids);
	}
}
