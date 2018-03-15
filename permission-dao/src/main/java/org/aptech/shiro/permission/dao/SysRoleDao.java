package org.aptech.shiro.permission.dao;
 
import org.aptech.shiro.permission.pojo.SysRole;
/**
 * 角色dao接口
 * @author Miao  Long
 *
 */
public interface SysRoleDao extends CommonDao<SysRole, Integer> {
	 
	void deleteRelaByIds(Integer [] ids);
}
