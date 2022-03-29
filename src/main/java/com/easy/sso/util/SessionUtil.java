package com.easy.sso.util;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.util.WebUtils;

import com.easy.sso.components.shiro.config.ShiroConfig;

public class SessionUtil {

	public static final String DEFAULT_REDIRECT = "redirect";

	/**
	 * 往 session中放入待跳转的地址
	 * 
	 * @param request
	 * @param url
	 */
	public static void setRedirect(ServletRequest request, String url) {
		set(request, DEFAULT_REDIRECT, url);
	}

	public static void set(ServletRequest request, String key, String value) {
		WebUtils.toHttp(request).getSession().setAttribute(key, value);
	}

	/**
	 * 取出 session中存的地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRedirect(ServletRequest request) {
		return get(request, DEFAULT_REDIRECT, true).toString();
	}

	public static Object get(ServletRequest request, String key) {
		return get(request, key, true);
	}

	public static Object get(ServletRequest request, String key, boolean isRemove) {
		HttpServletRequest req = WebUtils.toHttp(request);
		String value = req.getSession().getAttribute(key).toString();
		if (isRemove)
			req.getSession().removeAttribute(key);
		return value;
	}

	/**
	 * 重定向到默认页面
	 * 
	 * @param servletResponse
	 * @throw@Autowired s IOException
	 */
	public static void redirect(ServletResponse servletResponse) throws IOException {
		((HttpServletResponse) servletResponse).sendRedirect(ShiroConfig.DEFAULT_LOGIN_URL);
	}

}
