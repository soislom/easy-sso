package com.easy.sso.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RouterController {

	@RequestMapping("/login")
	public String loginPage() {
		return "/login";
	}

	/**
	 * 
	 * @param redirect 转发的路径
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/index")
	public void index(@RequestParam(name = "redirect", defaultValue = "#") String redirect,
			HttpServletResponse response) throws IOException {
		System.out.println(redirect);
		response.sendRedirect(redirect);
	}

}
