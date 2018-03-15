package org.aptech.shiro.permission.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import javax.annotation.Resource;
import javax.enterprise.inject.New;
import javax.jms.Session;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.aptech.shiro.permission.dao.SysPermissionDao;
import org.aptech.shiro.permission.dao.SysUserDao;
import org.aptech.shiro.permission.pojo.SysPermission;
import org.aptech.shiro.permission.pojo.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import freemarker.core.ReturnInstruction.Return;
/**
 * 用户控制器
 * @author Miao  Long
 *
 */
@Controller
@RequestMapping("/user")
public class SysUserController {

	@Resource
	private SysUserDao sysUserDao;
	
	@Resource
	private SysPermissionDao sysPermissionDao;
	
	@RequestMapping("/form")
	public String form() { 
		return "sysuser/user_form";
	}
	
	@RequestMapping("/index")
	@RequiresPermissions("user:delete")
	public String index() { 
		return "sysuser/index";
	}
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	/**
	 * 退出按钮
	 */
	@RequestMapping("/logout")
	public String exit(HttpSession session) {
		session.removeAttribute("permisssions");
		session.removeAttribute("menuList");
		session.removeAttribute("login_user");
		return "login";
	}
	
	/**
	 * 登录控制器
	 * @param user
	 * @param modelMap
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String login2(HttpSession session) {
		System.out.println("进入了post的login方法");
		 session.setAttribute("403", "您没有权限");
		 return "login";
	}
	
	@RequestMapping("/main")
	public String main(HttpSession session) {
		System.out.println("welcome!");
		Subject subject =  SecurityUtils.getSubject();
		SysUser user = (SysUser)subject.getPrincipal();
		session.setAttribute("login_user", user);
		return "main";
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(SysUser user,Integer [] rids) {
		System.out.println(user);
		Map<String , Object> map = new HashMap<>();
		Md5Hash hash = new Md5Hash(user.getPassword(),user.getSalt());
		user.setPassword(hash.toString());
		System.out.println(user);
		//添加用户
		sysUserDao.add(user);
		//添加关系
		sysUserDao.addUserRole(user.getId(), rids);
		map.put("result", true);
		return map;
	 
	}
	@RequestMapping(value= "getUserById",method = RequestMethod.POST)
	@ResponseBody
	public SysUser getUserById(Integer id) {
		return sysUserDao.getById(id);
	}	
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String,Object> list(Integer page,Integer rows,SysUser condition,@RequestParam(defaultValue="id")String sort,@RequestParam(defaultValue="asc")String order){
		int start = (page - 1)*rows;
		Map<String, Object> map = new HashMap<>();
		map.put("total", sysUserDao.getCount());
		map.put("rows", sysUserDao.getListByCondition(start,rows,condition,sort, order));
		return map;
	}
	@RequestMapping("/edit")
	@ResponseBody
	@RequiresPermissions("user:delete")
	public Map<String, Object> edit(SysUser user,Integer [] rids){
		System.out.println("123");
		Map<String, Object> map = new HashMap<>();
		//System.out.println(user);
		//先删除管理表
		sysUserDao.deleteRela(user.getId());
		sysUserDao.update(user);
		//System.out.println("123");
		//添加关系
		sysUserDao.addUserRole(user.getId(), rids);
		map.put("result",true);
		return map;
	}
	
	@RequestMapping("/batchDelete")
	@ResponseBody
	@RequiresPermissions("user:delete")
	public Map<String, Object> batchDelete(Integer [] ids){
		System.out.println("123");
		Map<String, Object> map = new HashMap<>();
		sysUserDao.deleteByIds(ids);
		map.put("result", true);
		return map;
	}
	
	
	
	
	
}
