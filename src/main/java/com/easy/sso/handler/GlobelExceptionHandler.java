package com.easy.sso.handler;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.easy.sso.Response;

/**
 * 处理全局异常
 * @author wuchen
 * @date 2022-03-28
 */
@RestControllerAdvice
public class GlobelExceptionHandler {

	// 捕捉shiro的异常
	@ExceptionHandler(ShiroException.class)
	public Response<Object> handleShiroException(ShiroException e) {
		return Response.builder(401, e.getMessage());
	}

	// 捕捉其他所有异常
	@ExceptionHandler(Exception.class)
	public Object globalException(HttpServletRequest request, Throwable e) {
		return Response.builder(401, e.getMessage());
	}
}
