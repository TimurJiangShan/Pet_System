package com.example.web.front;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.entity.Node;
import com.example.entity.Tag;
import com.example.entity.Topic;
import com.example.entity.User;
import com.example.exception.ApiAssert;
import com.example.util.CookieAndSessionUtil;
import com.example.util.bcrypt.BCryptPasswordEncoder;
import org.apache.commons.collections.map.HashedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dto.PageDataBody;
import com.example.dto.Result;
import com.example.dto.UserExecution;
import com.example.service.CollectService;
import com.example.service.FollowService;
import com.example.service.NodeService;
import com.example.service.NoticeService;
import com.example.service.ReplyService;
import com.example.service.NodeTabService;
import com.example.service.TopicService;
import com.example.service.UserService;

@Controller
public class IndexController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private NodeTabService nodeTabService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private CollectService collectDaoService;
	@Autowired
	private NodeService nodeService;

	@Autowired
	private FollowService followService;

	//main page
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	private String index(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "p", defaultValue = "1") Integer p,
			@RequestParam(value = "tab", defaultValue = "all") String tab,
			@RequestParam(value = "node", required = false) String node) {
		
		request.setAttribute("tab", tab);
		
		User user = getUser(request);
		
		if (user != null) {
			int countTopic = topicService.countByUserName(user.getUserName());
			int countCollect = collectDaoService.count(user.getUserId());
			int countFollow = followService.countByUid(user.getUserId());
			int countNotReadNotice = noticeService.countNotReadNotice(user.getUserName());
			int countScore = userService.countScore(user.getUserId());

			request.setAttribute("countTopic", countTopic);
			request.setAttribute("countCollect", countCollect);
			request.setAttribute("countFollow", countFollow);
			request.setAttribute("countNotReadNotice", countNotReadNotice);
			request.setAttribute("countScore", countScore);
		}
		
		if (StringUtils.isEmpty(node)) {
			Map<String, List<Topic>> map = new LinkedHashMap<>();
			map.put("hot", topicService.findIndexHot(1, 10, "hot").getList());
			map.put("new", topicService.findIndexHot(1, 10, "newest").getList());
			map.put("waiting", topicService.findIndexHot(1, 10, "noReply").getList());
			List<Node> nodeListForIndex = nodeService.listForIndex();
			nodeListForIndex.stream().forEach(n -> {
				map.put(n.getNodeTitle(), topicService.pageAllByTabAndNode(p, 10, tab, n.getNodeTitle()).getList());
			});
			request.setAttribute("map", map);
			request.setAttribute("nodeName", "index");
			return "index";
		} else {
			List<Node> hotNodeList = nodeService.findAll(0, 10); // hot
			int countUserAll = userService.countUserAll(); // membership num
			int countAllTopic = topicService.countAllTopic(null, null); // post num
			int countAllReply = replyService.countAll(); // comment num
			
			request.setAttribute("hotNodeList", hotNodeList);
			request.setAttribute("countUserAll", countUserAll);
			request.setAttribute("countAllTopic", countAllTopic);
			request.setAttribute("countAllReply", countAllReply);
			
			PageDataBody<Topic> page;
			
			if ("all".equals(node)) {
				page = topicService.pageAllByTabAndNode(p, 25, tab, null);
			} else {
				page = topicService.pageAllByTabAndNode(p, 25, tab, node);
			}
			
			request.setAttribute("page", page);
			request.setAttribute("nodeName", node);
			return "node";
		}
	}

	//register page
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	private String register(HttpServletRequest request) {
		return "register";
	}

	//register interface
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	private Result<UserExecution> register(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("email") String email,
			@RequestParam("userType") String userType, HttpServletRequest request, HttpServletResponse response) {
		ApiAssert.notEmpty(username, "input username");
		ApiAssert.notEmpty(password, "input password");
		ApiAssert.notEmpty(email, "input email");
		User user = userService.findByName(username);
		ApiAssert.isNull(user, "user exitn");
		user = userService.findByEmail(email);
		ApiAssert.isNull(user, "email exit");
		UserExecution save = userService.createUser(username, password, email, userType);
		// set session
		CookieAndSessionUtil.setSession(request, "user", save.getUser());
		return new Result<UserExecution>(true, save);
	}

	//login page
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	private String login(HttpServletRequest request) {
		return "login";
	}

	//login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	private Result<User> login(@RequestParam("username") String username,
							   @RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response) {
		User user = userService.findByName(username);
		ApiAssert.notNull(user, "user no exist");
		ApiAssert.isTrue(new BCryptPasswordEncoder().matches(password, user.getPassword()), "wrong password");
		// set session
		CookieAndSessionUtil.setSession(request, "user", user);
		return new Result<User>(true, user);
	}

	//logout
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	private String logout(HttpServletRequest request, HttpServletResponse response) {
		// stringRedisTemplate.delete("user");
		CookieAndSessionUtil.removeSession(request, "user");
		return "redirect:/";
	}

	//tage
	@RequestMapping(value = "/tags", method = RequestMethod.GET)
	private String tag(HttpServletRequest request, @RequestParam(value = "p", defaultValue = "1") Integer p) {
		PageDataBody<Tag> tag = topicService.findByTag(p, 50);
		request.setAttribute("tag", tag);
		return "tag/tag";
	}

	@RequestMapping(value = "/session", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, String> session(HttpServletRequest request) {
		User user = getUser(request);
		HashedMap map = new HashedMap();
		if (user != null) {
			map.put("success", true);
			map.put("user", user.getUserName());
			return map;
		} else {
			map.put("success", false);
			map.put("user", "");
			return map;
		}
	}

	//search
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	private String search(HttpServletRequest request, @RequestParam("s") String search,
			@RequestParam(value = "p", defaultValue = "1") Integer p) {
		if (search == null || search.equals("")) {
			return "search";
		}
		PageDataBody<Topic> pageLike = topicService.pageLike(p, 50, search);
		// BaseEntity baseEntity = new BaseEntity();
		// request.setAttribute("baseEntity", baseEntity);
		request.setAttribute("pageLike", pageLike);
		request.setAttribute("search", search);
		return "search/search";
	}

	/**
	 * Top100 standings
	 */
	@RequestMapping(value = "/top100")
	private String top100() {
		return "score/top100";
	}

	/**
	 * about
	 */
	@RequestMapping(value = "/about")
	private String about() {
		return "foot/about";
	}

	/**
	 * faq
	 * 
	 * @return
	 */
	@RequestMapping(value = "/faq")
	private String faq() {
		return "foot/faq";
	}

	/**
	 * api
	 * 
	 * @return
	 */
	@RequestMapping(value = "/api")
	private String api() {
		return "foot/api";
	}

	/**
	 * mission
	 * 
	 * @return
	 */
	@RequestMapping(value = "/mission")
	private String mission() {
		return "foot/mission";
	}

	/**
	 * advertise
	 * 
	 * @return
	 */
	@RequestMapping(value = "/advertise")
	private String advertise() {
		return "foot/advertise";
	}

}
