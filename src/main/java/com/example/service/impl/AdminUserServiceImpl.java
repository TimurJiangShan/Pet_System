package com.example.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.exception.ApiAssert;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.example.dao.AdminUserDao;
import com.example.dto.PageDataBody;
import com.example.entity.AdminUser;
import com.example.service.AdminUserService;

import com.example.util.SimpleHashUtil;


@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserDao adminUserDao;
	


	@Override
	public AdminUser getByName(String name) {
		return adminUserDao.selectByName(name);
	}

	@Override
	public AdminUser getById(Integer id) {
		return adminUserDao.selectById(id);
	}

	@Override
	public PageDataBody<AdminUser> page(Integer pageNumber, Integer pageSize) {
		List<AdminUser> list = adminUserDao.selectAll((pageNumber - 1) * pageSize, pageSize);
		int countAll = this.countAll();
		return new PageDataBody<>(list, pageNumber, pageSize, countAll);
	}

	@Override
	public PageDataBody<AdminUser> pageRoles(Integer pageNumber, Integer pageSize) {
		List<AdminUser> adminUsers = adminUserDao.selectAll((pageNumber - 1) * pageSize, pageSize);
		int countAll = this.countAll();
		return new PageDataBody<>(adminUsers, pageNumber, pageSize, countAll);
	}

	@Override
	public int countAll() {
		return adminUserDao.countAll();
	}

	@Transactional
	@Override
	public void save(String username, String password, String avatar) {
		AdminUser adminUser = getByName(username);
		ApiAssert.isNull(adminUser, "username exist");
		adminUser = new AdminUser();
		adminUser.setUsername(username);
		adminUser.setPassword(SimpleHashUtil.simpleHash("MD5", password, username, 1024).toString());
		adminUser.setAvatar(avatar);
		adminUser.setCreateDate(new Date());
		adminUserDao.insert(adminUser);
	}

	@Transactional
	@Override
	public Map<String,Object> update(Integer id, String password, String avatar) {
		Map<String,Object> map = new HashMap<>();
		boolean updatePassword = false;
		boolean updateAvatar = false;
		AdminUser adminUser = getById(id);

		if (!StringUtils.isEmpty(password)) {
			adminUser.setPassword(SimpleHashUtil.simpleHash("MD5", password, adminUser.getUsername(), 1024).toString());
			updatePassword = true;
		}

		if(!avatar.equals(adminUser.getAvatar())) {
			adminUser.setAvatar(avatar);
			updateAvatar = true;
		}

		if (updatePassword || updateAvatar) {
			adminUser.setUpdateDate(new Date());
			adminUserDao.update(adminUser);
		}



		AdminUser principal = (AdminUser)SecurityUtils.getSubject().getPrincipal();

		if(adminUser.getAdminUserId().equals(principal.getAdminUserId())) {
			map.put("logout", true);
		}
		return map;
	}

	@Transactional
	@Override
	public void removeById(Integer id) {
		adminUserDao.deleteById(id);
	}
}
