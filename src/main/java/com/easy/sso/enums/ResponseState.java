package com.easy.sso.enums;

/**
 * 全局返回状态码
 * 
 * @author wuchen
 * @date 2022-03-28
 *
 */
public enum ResponseState {

	UNAUTH(401, "没有权限访问"), 
	FAILED(402, "没有权限访问"), 
	SUCCESS(200, "操作成功");

	private Integer code;

	private String message;

	private ResponseState(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
