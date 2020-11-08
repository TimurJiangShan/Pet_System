package com.example.web.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import com.example.dto.Result;

@Controller
@RequestMapping("/admin/user")
public class UserAdminController {

	@Autowired
	private UserService userService;
	
	/**
	 * User List
	 * @param username
	 * @param email
	 * @param p
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(String username, String email, @RequestParam(value = "p",defaultValue = "1") Integer p, Model model) {
		if(StringUtils.isEmpty(username)) username = null;
		if(StringUtils.isEmpty(email)) email = null;
		model.addAttribute("username", username);
		model.addAttribute("email", email);
		model.addAttribute("p", p);
		model.addAttribute("page", userService.pageForAdmin(username, email, p, 25));
		return "/admin/user/list";
	}

	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public String edit(Integer id, Model model) {
		model.addAttribute("user", userService.findById(id));
		return "/admin/user/edit";
	}
	
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> edit(User user){
		userService.updateAdmin(user);
		return new Result<>(true, "Success");
	}
	
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@ResponseBody
	public Result<String> delete(Integer id){
		userService.deleteAdmin(id);
		return new Result<>(true, "Delete success");
	}
	
	/**
	 * refresh Token
	 * @return
	 */
	@RequestMapping(value = "/refreshToken",method = RequestMethod.GET)
	@ResponseBody
	public Result<String> refreshToken(){
		return new Result<>(true, StringUtil.getUUID());
	}
	

	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        // 转换日期 注意这里的转化要和传进来的字符串的格式一直 如2020-10-9 就应该为yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        // CustomDateEditor为自定义日期编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	/**
	 * block user
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/block",method = RequestMethod.GET)
	@ResponseBody
	public Result<String> block(@RequestParam(value = "id") Integer id, @RequestParam(value = "status") boolean status){
		User user = userService.findById(id);
		if (user == null) {
			return new Result<>(false, "User does not exists");
		}
		user.setIsBlock(status);
		userService.updateUser(user);
		return new Result<>(true, "Success");
	}
}
