package com.example.dao;

import java.util.List;

import com.example.entity.AdminUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author Jiangshan Zhao
 * @date 10/10/2020
 */
public interface AdminUserDao {

	AdminUser selectByName(@Param("name") String name);

	AdminUser selectById(@Param("id") Integer id);

	List<AdminUser> selectAll(@Param("start") Integer start,@Param("limit") Integer limit);

	int insert(AdminUser adminUser);

	int update(AdminUser adminUser);

	int deleteById(@Param("id") Integer id);

	int countAll();
	
}
