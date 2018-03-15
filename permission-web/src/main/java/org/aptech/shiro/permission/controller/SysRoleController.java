package org.aptech.shiro.permission.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.enterprise.inject.Default;
import javax.management.relation.Role;
import javax.persistence.Index;
import javax.ws.rs.DefaultValue;

import org.apache.ibatis.annotations.Param;
import org.aptech.shiro.permission.dao.SysRoleDao;
import org.aptech.shiro.permission.dao.SysUserDao;
import org.aptech.shiro.permission.pojo.SysRole;
import org.junit.runners.Parameterized.Parameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 角色控制器
 * @author Miao  Long
 *
 */
@Controller
@RequestMapping("/role")
public class SysRoleController {

	@Resource
	private SysRoleDao sysRoleDao;
	
	@RequestMapping("/index")
	public String Index() {
		return "sysrole/index";
	}
	@RequestMapping("/toallot")
	public String allot(Integer rid,ModelMap modelMap) {
		modelMap.put("rid", rid);
		return "syspermission/allot_form";
	}
	@RequestMapping("/role_form")
	public String toAdd() {
		return "sysrole/role_form";
	}
	
	@RequestMapping("/all")
	@ResponseBody
	public List<SysRole> all(){
		List<SysRole> list = new ArrayList<>(); 
		return sysRoleDao.getAll();
	}
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(SysRole role){
		Map<String, Object> map = new HashMap<>();
		sysRoleDao.add(role);
		map.put("result", true);
		return map;
	}
	@RequestMapping("/selectRole")
	@ResponseBody
	public SysRole toEdit(Integer rid) {
		return sysRoleDao.getById(rid);
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Map<String, Object> edit(SysRole role){
		Map<String, Object> map = new HashMap<>();
		sysRoleDao.update(role);
		map.put("result", true);
		return map;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(Integer [] ids){
		Map<String, Object> map = new HashMap<>();
		//先删除关系表
		sysRoleDao.deleteRelaByIds(ids);;
		sysRoleDao.deleteByIds(ids);
		map.put("result",true);
		return map;
	}
	
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(Integer page,SysRole role,Integer rows,@RequestParam(defaultValue="id")String sort,@RequestParam(defaultValue = "asc")String order){
		int start = (page-1)*rows;
		Map<String ,Object> map = new HashMap<>();
		map.put("rows", sysRoleDao.getListByCondition(start, rows, role,sort,order));
		map.put("total",sysRoleDao.getCountByCondition(role));
		return map;
	}
	
	
	
	
	
	
}
