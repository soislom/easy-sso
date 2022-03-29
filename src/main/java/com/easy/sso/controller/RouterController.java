package com.easy.sso.controller;

import java.io.IOException;

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
	public String indexPage(@RequestParam(value = "access_token", required = false) String access_token) throws IOException {
		return "/index";
	}

}
