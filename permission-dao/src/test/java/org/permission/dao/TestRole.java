package org.permission.dao; 
import java.util.List;

import javax.annotation.Resource;

import org.aptech.shiro.permission.dao.SysRoleDao;
import org.aptech.shiro.permission.dao.SysUserDao;
import org.aptech.shiro.permission.pojo.SysRole;
import org.aptech.shiro.permission.pojo.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestRole {

	@Resource
	private SysRoleDao sysRoleDao;
	
	
	public void setSysUserDao(SysRoleDao sysRoleDao) {
		this.sysRoleDao = sysRoleDao;
	}
	@Test
	public void TestUser()  {
//		 List<SysRole> list = sysRoleDao.getAll();
//		 for (SysRole sysRole : list) {
//			 for (SysUser sysUser : sysRole.getUsers()) {
//				System.out.println(sysUser);
//			}
//			 System.out.println("---------------------------");
//		 }
	}
	@Test
	public void Testadd()  {
//		 SysRole role = new SysRole();
//		 role.setName("CEO");
//		 role.setAvailable(1);
//		 sysRoleDao.add(role);
//		 System.out.println(role);
	}
	@Test
	public void TestUpdate()  {
//		 SysRole role = new SysRole();
//		 role.setId(4);
//		 role.setName("CEO12");
//		 role.setAvailable(0);
//		 sysRoleDao.update(role);
		  
	}
	@Test
	public void TestdeleteByids()  {
		
//		Integer [] ids = {8,9,10,11};
//		  sysRoleDao.deleteByIds(ids);
	}
	@Test
	public void TestGetByCondition()  {
//		 SysRole role = new SysRole(); 
//		 role.setName("CEo");
//		List<SysRole> list = sysRoleDao.getListByCondition(1, 5, role, "id", "asc");
//		for (SysRole sysRole : list) {
//			System.out.println(sysRole);
//		}
//		
//		System.out.println(sysRoleDao.getCountByCondition(role));
	}
	
	
	 
}
