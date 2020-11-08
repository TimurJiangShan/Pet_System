package com.example.web.front;

import javax.servlet.http.HttpServletRequest;

import com.example.entity.Notice;
import com.example.entity.User;
import com.example.service.CollectService;
import com.example.service.NoticeService;
import com.example.service.TopicService;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.PageDataBody;

@Controller
public class NoticeController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NoticeService rootNoticeService;
	@Autowired
	private UserService rootUserService;
	@Autowired
	private TopicService rootTopicService;
	@Autowired
	private CollectService collectDaoService;

	@RequestMapping(value = "/notification/list", method = RequestMethod.GET)
	private String noticeList(HttpServletRequest request,@RequestParam(value = "p", defaultValue = "1") Integer p) {

		User user = getUser(request);
		if(user == null) {
			return "error-page/500";
		}
		int countByAuthor = rootNoticeService.countByAuthor(user.getUserName());
		int notReadNotice = rootNoticeService.countNotReadNotice(user.getUserName());
		int countTopicByUserName = rootTopicService.countByUserName(user.getUserName());
		int countCollect = collectDaoService.count(user.getUserId());
		PageDataBody<Notice> page = rootNoticeService.pageByAuthor(p, 20, user.getUserName());
		rootNoticeService.updateIsRead(user.getUserName());
		request.setAttribute("countByAuthor", countByAuthor);
		request.setAttribute("notReadNotice", notReadNotice);
		request.setAttribute("page", page);
		request.setAttribute("user", user);
		request.setAttribute("countTopicByUserName", countTopicByUserName);
		request.setAttribute("countCollect", countCollect);
		return "notification/list";
	}
}
