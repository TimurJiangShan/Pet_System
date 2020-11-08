package com.example.web.front;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.entity.ReplyAndTopicByName;
import com.example.entity.Topic;
import com.example.entity.User;
import com.example.entity.Visit;
import com.example.exception.ApiAssert;
import com.example.store.StorageService;
import com.example.util.bcrypt.BCryptPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.base.BaseEntity;
import com.example.dto.PageDataBody;
import com.example.dto.Result;
import com.example.dto.UserExecution;
import com.example.service.CollectService;
import com.example.service.NoticeService;
import com.example.service.ReplyService;
import com.example.service.TopicService;
import com.example.service.UserService;
import com.example.service.VisitService;

@Controller
public class UserController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ReplyService rootReplyService;
	@Autowired
	private UserService rootUserService;
	@Autowired
	private TopicService rootTopicService;
	@Autowired
	private CollectService collectDaoService;
	@Autowired
	private NoticeService rootNoticeService;
	@Autowired
	private VisitService visitService;
	@Autowired
	private StorageService storageService;
	/**
	 * mainpage
	 */
	@RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
	private String detail(@PathVariable String name, Model model,@RequestParam(value = "tp", defaultValue = "1") Integer tp,@RequestParam(value = "rp", defaultValue = "1") Integer rp,HttpServletRequest request) {
		if(name == null) {
			return "error-page/404";
		}
		User user = null;
		user = rootUserService.findByName(name);
		if(user == null) {
			return "error-page/404";
		}
		User user2 = getUser(request);//current user

		PageDataBody<Topic> topicPage = rootTopicService.pageByAuthor(tp, 5, name);
		PageDataBody<ReplyAndTopicByName> replyPage = rootReplyService.findAllByNameAndTopic(name, rp, 5);

		int countTopic = rootTopicService.countByUserName(user.getUserName());//topic num
		int countCollect = collectDaoService.count(user.getUserId());//collection
		int countReply = rootReplyService.countByName(name);//comment num
		int countScore = rootUserService.countScore(user.getUserId());//
		int countVisit = visitService.count(user.getUserId());//visit num
		//after login if visiter not user ,add record
		if(user2 != null && user.getUserId() != user2.getUserId()) {
			Visit visit = new Visit();
			visit.setUid(user2.getUserId());
			visit.setVid(user.getUserId());
			visit.setCreateDate(new Date());
			visit.setDelete(false);
			visitService.save(visit);
		}
		model.addAttribute("user", user);
		model.addAttribute("user2", user2);
		model.addAttribute("replyPage", replyPage);
		model.addAttribute("topicPage", topicPage);
		model.addAttribute("countTopic", countTopic);
		model.addAttribute("countCollect", countCollect);
		model.addAttribute("countReply", countReply);
		model.addAttribute("countScore", countScore);
		model.addAttribute("countVisit", countVisit);
		return "user/detail";
	}
	
	//more topic
	@RequestMapping(value = "/user/topics", method = RequestMethod.GET)
	private String topics(Model model,@RequestParam(value = "p", defaultValue = "1") Integer p,HttpServletRequest request) {
		User user2 = getUser(request);//current user
		if(user2 == null) return "error-page/404.jsp";
		PageDataBody<Topic> topicPage = rootTopicService.pageByAuthor(p, 50, user2.getUserName());
		int countCollect = collectDaoService.count(user2.getUserId());//collect num
		int countTopicByUserName = rootTopicService.countByUserName(user2.getUserName());//post num
		int notReadNotice = rootNoticeService.countNotReadNotice(user2.getUserName());//unread num
		BaseEntity baseEntity = new BaseEntity();
		model.addAttribute("baseEntity", baseEntity);
		model.addAttribute("user", user2);
		model.addAttribute("topicPage", topicPage);
		request.setAttribute("countCollect", countCollect);
		request.setAttribute("countTopicByUserName", countTopicByUserName);
		request.setAttribute("notReadNotice", notReadNotice);
		return "user/topics";
	}
	
	@RequestMapping(value = "/user/{name}/replys", method = RequestMethod.GET)
	private String replys(@PathVariable String name, Model model,@RequestParam(value = "p", defaultValue = "1") Integer p,HttpServletRequest request) {
		if(name == null) {
			return "error-page/404.jsp";
		}
		User user = null;
		user = rootUserService.findByName(name);
		if(user == null) {
			return "error-page/404.jsp";
		}
		User user2 = getUser(request);//current user
		PageDataBody<ReplyAndTopicByName> replyPage = rootReplyService.findAllByNameAndTopic(name, p, 20);
		int countTopic = rootTopicService.countByUserName(user.getUserName());//topic num
		int countCollect = collectDaoService.count(user.getUserId());//collect num
		int countReply = rootReplyService.countByName(user.getUserName());//comment num
		model.addAttribute("user", user);
		model.addAttribute("user2", user2);
		model.addAttribute("replyPage", replyPage);
		model.addAttribute("countTopic", countTopic);
		model.addAttribute("countCollect", countCollect);
		model.addAttribute("countReply", countReply);

		return "user/replys";
	}
	
	/**
	 * personal setting page
	 */
	@RequestMapping(value = "/user/settings/profile", method = RequestMethod.GET)
	private String setting(HttpServletRequest request) {
		
		User user2 = getUser(request);
		if(user2 == null) {
			return "error-page/500";
		}
		request.setAttribute("user", user2);
		return "user/profile";
		
	}
	
	/**
	 * update settings
	 */
	@RequestMapping(value = "/user/setting/profile", method = RequestMethod.POST)
	private String updateUserInfo(String email,String url,String thirdId,String userAddr,
			String signature,HttpServletRequest request,HttpServletResponse response) {
		User user = getUser(request);
		if(user != null) {
			user.setEmail(email);
			user.setUrl(url);
			user.setThirdId(thirdId);
			user.setUserAddr(userAddr);
			user.setSignature(signature);
			rootUserService.updateUser(user);
			try {
				response.sendRedirect("/user/settings/profile");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "error-page/500";
	}
	
	/**
	 * update avatar
	 */
	@RequestMapping(value = "/user/settings/changeAvatar", method = RequestMethod.GET)
	private String changeAvatar(HttpServletRequest request) {
		return "user/changeAvatar";
	}
	
	/**
	 * update ava
	 */
	@RequestMapping(value = "/user/setting/changeAvatar", method = RequestMethod.POST)
	@ResponseBody
	private Result<String> changeAvatar(String avatarBase64,String path,HttpServletRequest request){
		User user = getUser(request);
		ApiAssert.notNull(user, "login first");
		ApiAssert.notEmpty(avatarBase64, "avatar not null");
		rootUserService.updateAvatar(avatarBase64, path, user, request);
		return new Result<>(true, "update success");
	}
	
	/**
	 * change the password
	 */
	@RequestMapping(value = "/user/settings/changePassword",method = RequestMethod.GET)
	private String changePassword(HttpServletRequest request) {
		return isLogin(request, "error-page/500", "user/changePassword");
	}
	
	@RequestMapping(value = "/user/setting/changePassword",method = RequestMethod.POST)
	@ResponseBody
	private Result<UserExecution> changePassword(HttpServletRequest request,String oldPassword,String newPassword){
		if(newPassword == null) {
			return new Result<>(false,"password not null");
		}
		User user = getUser(request);
		if(!user.getPassword().equals(oldPassword)) {
			return new Result<>(false,"password error");
		}
		//	encryption
		user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
		UserExecution updateUser = rootUserService.updateUser(user);
		return new Result<UserExecution>(true,updateUser);
	}
}
