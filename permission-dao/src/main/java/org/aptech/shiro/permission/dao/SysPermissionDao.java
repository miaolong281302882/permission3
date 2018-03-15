package org.aptech.shiro.permission.dao;  
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.aptech.shiro.permission.pojo.SysPermission;
/**
 * 权限dao接口  
 */
public interface SysPermissionDao extends CommonDao<SysPermission, Integer> {

	List<Integer> getPerIdsByRid(Integer rid);
	
	void deletePersByRid(Integer rid);
	
	void allotPer(@Param("perIds")List<Integer> perIds,@Param("rid")Integer rid);

	List<SysPermission> getPersByUserId(Integer userId);
	
	List<String> getPermissionsByUserId(Integer userId);
}
