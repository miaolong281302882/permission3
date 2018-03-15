package org.aptech.shiro.permission.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aptech.shiro.permission.pojo.SysUser;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		SysUser user = (SysUser)request.getSession().getAttribute("login_user");
		if (user == null){
			request.getRequestDispatcher("/user/login").forward(request, response);
			return false;
		}
		System.out.println("进入了拦截器！");
		return true;
	}
}
