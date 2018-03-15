package org.aptech.shiro.permission.dao;

import org.apache.ibatis.annotations.Param;
import org.aptech.shiro.permission.pojo.SysUser;
/**
 * 用户dao接口
 * @author Miao  Long
 *
 */
public interface SysUserDao extends CommonDao<SysUser, Integer> {

	 void addUserRole(@Param("userId")Integer userId,@Param("rids")Integer [] rids);
	 
	 void deleteRela(@Param("userId")Integer userId);
	 
	 SysUser selectByName(SysUser user);
}
