package com.easy.sso.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.easy.sso.Response;
import com.easy.sso.components.redis.RedisUtils;
import com.easy.sso.components.shiro.JwtUtils;
import com.easy.sso.pojo.User;

@Controller
public class LoginController {

	private @Autowired RedisUtils redisUtils;

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
			redisUtils.expire(jwtToken, 30 * 60);
			if (redirect != null) {
				return "redirect:" + redirect + "?access_token=" + jwtToken;
			} else {
				return "/index?access_token=" + jwtToken;
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

	@GetMapping("/logout")
	public Object logout() {
		return Response.success("退出登录");
	}
}
