package org.aptech.shiro.permission.controller;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import javax.annotation.Resource;
import org.aptech.shiro.permission.dao.SysPermissionDao;
import org.aptech.shiro.permission.pojo.SysPermission;
import org.aptech.shiro.permission.pojo.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 权限控制器
 * @author Miao  Long
 *
 */
@Controller
@RequestMapping("/permission")
public class SysPermissionController {
	@Resource
	private SysPermissionDao sysPermissionDao;
	//测试提交
	@RequestMapping("/form")
	public String form() { 
		return "syspermission/permission_form";
	}
	
	@RequestMapping("/index")
	public String index() { 
		return "syspermission/index";
	}
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(SysUser user,Integer [] rids) {
		System.out.println(user);
		Map<String , Object> map = new HashMap<>();
		 
		map.put("result", true);
		return map;
	}
	
	@RequestMapping("/getpermissions")
	@ResponseBody
	public List<Integer> getPermissionsByrid(Integer rid){
		System.out.println(sysPermissionDao.getPerIdsByRid(rid).size());
		return  sysPermissionDao.getPerIdsByRid(rid);
	}
	
	//分配权限
	@RequestMapping("/allotPer")
	@ResponseBody
	public Map<String, Object> allotPer(Integer [] ids,Integer rid){
		Map<String, Object> map = new HashMap<>();
		sysPermissionDao.deletePersByRid(rid); 
		sysPermissionDao.allotPer(Arrays.asList(ids), rid);
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value= "getUserById",method = RequestMethod.POST)
	@ResponseBody
	public SysPermission getUserById(Integer id) {
		return sysPermissionDao.getById(id);
	}	
	  
	@RequestMapping("/list")
	@ResponseBody
	public List<SysPermission> list(Integer page,Integer rows,SysPermission condition,@RequestParam(defaultValue="id")String sort,@RequestParam(defaultValue="asc")String order){
		System.out.println(sysPermissionDao);
		//		Integer start = (page - 1)*rows;
//		Map<String, Object> map = new HashMap<>();
//		map.put("total", sysPermissionDao.getCount());
//		map.put("rows", sysPermissionDao.getListByCondition(start, rows, condition, sort, order));
		List<SysPermission> list = sysPermissionDao.getAll();
		for (SysPermission sysPermission : list) {
			System.out.println(sysPermission);
		}
		return list;
	}
	@RequestMapping("/edit")
	@ResponseBody
	public Map<String, Object> edit(SysUser user,Integer [] rids){
		Map<String, Object> map = new HashMap<>();
		 
		map.put("result",true);
		return map;
	}
	
	@RequestMapping("/batchDelete")
	@ResponseBody
	public Map<String, Object> batchDelete(Integer [] ids){
		Map<String, Object> map = new HashMap<>();
		sysPermissionDao.deleteByIds(ids);
		map.put("result", true);
		return map;
	}
	
	
	
	
	
}
