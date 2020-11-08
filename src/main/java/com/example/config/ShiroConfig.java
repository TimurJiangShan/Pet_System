package com.example.config;

import java.util.LinkedHashMap;


public class ShiroConfig {

	//shiro config must pass
	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		

		LinkedHashMap<String, String> map = new LinkedHashMap<>();

		map.put("/resources/**", "anon");

		map.put("/admin/logout", "logout");
		
		map.put("/admin/**", "authc");
		
		return map;
	}
}
