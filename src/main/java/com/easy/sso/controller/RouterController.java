package com.easy.sso.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {

	@RequestMapping("/login")
	public String loginPage() {
		return "/login";
	}

	@RequestMapping("/index")
	public String indexPage() throws IOException {
		return "/index";
	}

}
