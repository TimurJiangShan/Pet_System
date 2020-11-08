package com.example.service;

import java.util.Map;
import com.example.dto.PageDataBody;
import com.example.entity.AdminUser;

public interface AdminUserService {

	AdminUser getByName(String name);
	
	AdminUser getById(Integer id);
	
	PageDataBody<AdminUser> page(Integer pageNumber, Integer pageSize);

	PageDataBody<AdminUser> pageRoles(Integer pageNumber, Integer pageSize);

	void save(String username,String password,String avatar);

	Map<String,Object> update(Integer id,String password,String avatar);
	
	void removeById(Integer id);
	
	int countAll();
}
