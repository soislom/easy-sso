package com.easy.sso.components.annonations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.easy.sso.components.redis.RedisConfig;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@Import(RedisConfig.class)
@PropertySource(value = {
		"classpath:/index.properties" }, ignoreResourceNotFound = true, encoding = "UTF-8", name = "index.properties")
public @interface EnableRedisCache {

}
