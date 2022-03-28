package com.easy.sso.consumer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.easy.sso.components.shiro.UserAuthInterface;
import com.easy.sso.pojo.User;

@Component
public class ExampleBean implements UserAuthInterface{

	public static Map<String, User> userMap = new HashMap<String, User>(16);
	public static Map<String, Set<String>> roleMap = new HashMap<String, Set<String>>(16);
	public static Map<String, Set<String>> permMap = new HashMap<String, Set<String>>(16);

	static {
		User user1 = new User("graython", "dd524c4c66076d1fa07e1fa1c94a91233772d132");
		User user2 = new User("plum", "cce369436bbb9f0325689a3a6d5d6b9b8a3f39a0");

		userMap.put("graython", user1);
		userMap.put("plum", user2);

		roleMap.put("graython", new HashSet<String>() {
			{
				add("admin");

			}
		});

		roleMap.put("plum", new HashSet<String>() {
			{
				add("guest");
			}
		});
		permMap.put("plum", new HashSet<String>() {
			{
				add("article:read");
			}
		});
	}

	@Override
	public User selectOne(String username) {
		return userMap.get(username);
	}

	@Override
	public Set<String> selectRoles(String username) {
		return roleMap.get(username);
	}

	@Override
	public Set<String> selectPrimess(String username) {
		return permMap.get(username);
	}
}
