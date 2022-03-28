package com.easy.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.easy.sso.components.filter.JwtFilter;
import com.easy.sso.components.spring.SpringContextUtil;

@Controller
public class RouterController {

	@RequestMapping("/login")
	public String loginPage() {
		JwtFilter jwtFilter = SpringContextUtil.getBean("jwtFilter", JwtFilter.class);
		System.out.println(jwtFilter);
		return "/login";
	}

	@RequestMapping("/index")
	public String index(@RequestParam(name = "sessionId", defaultValue = "#") String sessionId) {
		System.out.println(sessionId);
		return "/index";
	}

}
