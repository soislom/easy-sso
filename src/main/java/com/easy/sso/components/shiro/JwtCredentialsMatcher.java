package com.easy.sso.components.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

/**
 * 验证凭证是否合法
 * 
 * @author wuchen
 * @date 2022-03-28
 *
 */
public class JwtCredentialsMatcher implements CredentialsMatcher {

	private static final Logger logger = LoggerFactory.getLogger(JwtCredentialsMatcher.class.getName());

	/**
	 * JwtCredentialsMatcher只需验证JwtToken内容是否合法
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {

		String token = authenticationToken.getCredentials().toString();
		String username = authenticationToken.getPrincipal().toString();
		try {
			Algorithm algorithm = Algorithm.HMAC256(JwtUtils.SECRET);
			JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
			verifier.verify(token);
			return true;
		} catch (JWTVerificationException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

}
