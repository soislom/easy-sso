package com.easy.sso.components.shiro;

import java.util.Set;

import com.easy.sso.pojo.User;

public interface UserAuthInterface {

	User selectOne(String username);
	
	Set<String> selectRoles(String username);
	
	Set<String> selectPrimess(String username);

}
