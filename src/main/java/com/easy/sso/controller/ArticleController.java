package com.easy.sso.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easy.sso.Response;

@RestController
@RequestMapping("/article")
public class ArticleController {

	@GetMapping("/delete")
	@RequiresRoles(value = { "admin" })
	public Response<Object> deleteArticle(ModelMap model) {
		return Response.success("文章删除成功");
	}

	@GetMapping("/read")
	@RequiresPermissions(value = { "article:read" })
	public Response<Object> readArticle(ModelMap model) {
		return Response.success("请你鉴赏！");
	}

}
