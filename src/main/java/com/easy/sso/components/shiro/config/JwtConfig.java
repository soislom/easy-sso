package com.easy.sso.components.shiro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * jwt 配置信息
 * 
 * @author wuchen
 * @date 2022-03-28
 *
 */
@Configuration
public class JwtConfig {

	@Value("#[${easy.sso.jwt.expire.time}]")
	private long expireTime;

	@Value("${easy.sso.jwt.secret}")
	private String secret;

	@Value("${easy.sso.jwt.secret}")
	private String authHeader;

	public long getExpireTime() {
		return expireTime;
	}

	public String getSecret() {
		return secret;
	}

	public String getAuthHeader() {
		return authHeader;
	}

}
