package com.easy.sso.util;

import java.io.IOException;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easy.sso.components.shiro.config.ShiroConfig;

public class SessionUtil {

	public static final String DEFAULT_REDIRECT = "redirect";

	/**
	 * 	往 session中放入待跳转的地址
	 * @param request
	 * @param url
	 */
	public static void set(HttpServletRequest request, String url) {
		request.getSession().setAttribute(DEFAULT_REDIRECT, url);
	}

	/**
	 * 	取出 session中存的地址
	 * @param request
	 * @return
	 */
	public static String get(HttpServletRequest request) {
		String redirect = request.getSession().getAttribute(DEFAULT_REDIRECT).toString();
		request.getSession().removeAttribute(DEFAULT_REDIRECT);
		return redirect;
	}
	
	/**
	 * 	重定向到默认页面
	 * @param servletResponse
	 * @throws IOException
	 */
	public static void redirect(ServletResponse servletResponse) throws IOException {
		((HttpServletResponse) servletResponse).sendRedirect(ShiroConfig.DEFAULT_LOGIN_URL);
	}

}
