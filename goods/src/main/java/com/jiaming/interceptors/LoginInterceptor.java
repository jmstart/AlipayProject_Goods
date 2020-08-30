package com.jiaming.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jiaming.entity.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	// true 不拦截 false 拦截
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {

			return true;// 放行
		} else {
			// 判断 一下 当前的请求 是不是一个 异步请求
			// 异步请求的请求头中X-Requested-With 对应的值XMLHttpRequest
			if (request.getHeader("X-Requested-With") != null
					&& request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {

				if (request.getRequestURI().contains("verifyPwd.action")) {
					System.out.println("进入到了 这个拦截器判断。。。。。。");
					return false;
				}
			}
			response.getWriter().print("<script>alert('请先登录')</script>");
			return false;// 拦截
		}
	}
}
