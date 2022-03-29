package com.easy.sso.components.redis;

import org.springframework.beans.factory.annotation.Value;

public class RedisConfig {

	public static String host;
	public static String password;

	@Value("${spring.redis.host}")
	public void setHost(String host) {
		RedisConfig.host = (host == null ? "127.0.0.1" : host);
	}

	@Value("${spring.redis.password}")
	public void setPassword(String password) {
		RedisConfig.password = (password == null ? "127.0.0.1" : password);
	}

}
