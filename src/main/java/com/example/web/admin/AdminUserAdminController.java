package com.example.web.admin;

import java.util.Map;


import com.example.entity.AdminUser;
import com.example.exception.ApiAssert;
import com.example.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.dto.PageDataBody;
import com.example.dto.Result;


@Controller
@RequestMapping("/admin/admin_user")
public class AdminUserAdminController {

	@Autowired
	private AdminUserService adminUserService;



	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model modle, @RequestParam(defaultValue = "1") Integer p) {
		PageDataBody<AdminUser> page = adminUserService.pageRoles(p, 5);
		modle.addAttribute("page", page);
		modle.addAttribute("p", p);
		return "admin/admin_user/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		return "admin/admin_user/add";
	}


	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> add(String username, String password, String avatar) {
		ApiAssert.notNull(username, "Username cannot be empty");
		ApiAssert.notNull(password, "Password cannot be empty");
		if(StringUtils.isEmpty(avatar)) avatar = "/resources/images/default-avatar.jpg";
		adminUserService.save(username, password, avatar);
		return new Result<>(true, "Add user success");
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, Integer id) {
		AdminUser adminUser = adminUserService.getById(id);
		model.addAttribute("adminUser", adminUser);
		return "admin/admin_user/edit";
	}

	/**
	 * if password empty，do not update password
	 * if avatar empty，set avatar default
	 * if the user edited is current user, then relogin forcely
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Result<Map<String, Object>> edit(Integer id, String password, String avatar) {
		if(StringUtils.isEmpty(avatar)) avatar = "/resources/images/default-avatar.jpg";
		// Update User
		Map<String, Object> map = adminUserService.update(id, password, avatar);
		return new Result<Map<String,Object>>(true, map);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Integer id) {
		adminUserService.removeById(id);
		return "redirect: /admin/admin_user/list";
	}

}
