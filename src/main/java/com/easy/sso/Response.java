package com.easy.sso;

import com.easy.sso.enums.ResponseState;

/**
 * 响应数据格式
 * 
 * @author wuchen
 * @date 2022-03-28
 *
 * @param <T>
 */
public class Response<T> {

	private static final Integer SUCCESS_CODE = 200;
	private static final Integer FAILED_CODE = 500;

	private int code;

	private String message;

	private T data;

	private Response(ResponseState state) {
		this(state.getCode(), state.getMessage());
	}

	private Response(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	private Response(int code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <T> Response<T> success(String message) {
		return builder(SUCCESS_CODE, message, null);
	}
	public static <T> Response<T> success(String message, T value) {
		return builder(SUCCESS_CODE, message, value);
	}

	public static <T> Response<T> builder(ResponseState state) {
		return builder(state.getCode(), state.getMessage(), null);
	}

	public static <T> Response<T> builder(Integer code, String message) {
		return new Response<T>(code, message, null);
	}

	public static <T> Response<T> builder(Integer code, String message, T data) {
		return new Response<T>(code, message, data);
	}

	public static <T> Response<T> failed(String message) {
		return builder(FAILED_CODE, message, null);
	}

}