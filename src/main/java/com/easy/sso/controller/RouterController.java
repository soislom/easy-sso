package com.easy.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RouterController {

	@RequestMapping("/login")
	public String loginPage() {
		return "/login";
	}

	@RequestMapping("/index")
	public String index(@RequestParam(name = "sessionId", defaultValue = "#") String sessionId) {
		System.out.println(sessionId);
		return "/index";
	}

}
