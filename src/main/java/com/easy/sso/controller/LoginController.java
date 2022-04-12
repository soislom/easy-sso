package com.easy.sso.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easy.sso.Response;
import com.easy.sso.components.shiro.JwtToken;
import com.easy.sso.components.shiro.JwtUtils;
import com.easy.sso.enums.ResponseState;
import com.easy.sso.pojo.User;
import com.easy.sso.util.Assert;

@Controller
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class.getName());

	@PostMapping("/login.json")
	public String userLogin(@ModelAttribute User user,
			@RequestParam(value = "redirect", required = false) String redirect, HttpServletResponse response,
			HttpServletRequest request) throws IOException, ServletException {
		// 获取当前用户主体
		Subject subject = SecurityUtils.getSubject();
		String msg = null;
		// 将用户名和密码封装成 UsernamePasswordToken 对象
		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
		try {
			subject.login(token);
			msg = "登录成功。";
			// 若登录成功，签发 JWT token
			String jwtToken = JwtUtils.sign(user.getName(), JwtUtils.SECRET);
			// 将签发的 JWT token 设置到 HttpServletResponse 的 Header 中
			((HttpServletResponse) response).setHeader(JwtUtils.AUTH_HEADER, jwtToken);
			if (redirect != null) {
				return "redirect:" + redirect + "?access_token=" + jwtToken;
			} else {
				request.getSession().setAttribute("name", user.getName());
				return "/index";
			}
		} catch (UnknownAccountException uae) { // 账号不存在
			msg = "用户名与密码不匹配，请检查后重新输入！";
		} catch (IncorrectCredentialsException ice) { // 账号与密码不匹配
			msg = "用户名与密码不匹配，请检查后重新输入！";
		} catch (LockedAccountException lae) { // 账号已被锁定
			msg = "该账户已被锁定，如需解锁请联系管理员！";
		} catch (AuthenticationException ae) { // 其他身份验证异常
			msg = "登录异常，请联系管理员！";
		}
		request.setAttribute("msg", msg);
		return "/login";
	}

	/**
	 * 认证参数是否有效
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/auth")
	@ResponseBody
	public Response<Object> auth(HttpServletRequest request, HttpServletResponse response) {
		AuthenticationToken token = createToken(request, response);
		if (token == null) {
			String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken "
					+ "must be created in order to execute a login attempt.";
			throw new IllegalStateException(msg);
		}

		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token); // 交给 Shiro 去进行登录验证
			log.info("{} auth success", token);
			return Response.builder(ResponseState.SUCCESS);
		} catch (AuthenticationException e) {
			log.info("{} auth failed: {}", token, e.getMessage());
			return Response.builder(ResponseState.FAILED);
		}
	}

	private AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		String authorization = httpServletRequest.getHeader(JwtUtils.AUTH_HEADER);
		if (authorization == null) {
			authorization = servletRequest.getParameter("access_token");
		}
		JwtToken token = new JwtToken(authorization);
		return token;
	}

	/**
	 * 获取token 签发时间
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("/expireDate")
	public Response<Date> queryExpireDate(@RequestParam(value = "access_token", required = false) String token,
			HttpServletRequest request) {
		if (token == null)
			token = request.getHeader(JwtUtils.AUTH_HEADER);
		Assert.isNull(token, "token cannot be null");
		Date suedAt = JwtUtils.getIssuedAt(token);
		return Response.success("操作成功", suedAt);
	}

	@GetMapping("/logout")
	public Object logout() {
		return Response.success("退出登录");
	}
}
