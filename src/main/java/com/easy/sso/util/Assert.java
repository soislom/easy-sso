package com.easy.sso.util;

/**
 * 断言工具类
 * 
 * @author wuchen
 * @date 2022-03-29
 *
 */
public class Assert {

	/**
	 * 	断言参数不能空
	 * @param value
	 */
	public static void isNull(Object value) {
		isNull(value, "参数不能为空");
	}

	/**
	 * 	断言参数不能空
	 * @param value
	 * @param message
	 */
	public static void isNull(Object value, String message) {
		if (null == value) {
			throw new NullPointerException(message);
		}
	}

}
