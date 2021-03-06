package com.example.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.config.SiteConfig;
import com.example.entity.ReplyAndTopicByName;
import com.example.entity.Top100;
import com.example.entity.Topic;
import com.example.entity.User;
import com.example.exception.ApiAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PageDataBody;
import com.example.dto.Result;
import com.example.service.CollectService;
import com.example.service.FollowService;
import com.example.service.NoticeService;
import com.example.service.ReplyService;
import com.example.service.TopicService;
import com.example.service.UserService;
import com.example.service.VisitService;
import com.example.web.front.BaseController;

/**
 * 
 * @author Jiangshan
 * 11/01/2020
 * TODO
 */
@RestController
public class UserApiController extends BaseController{

	@Autowired
	private CollectService collectDaoService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private UserService userService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private FollowService followService;
	@Autowired
	private VisitService visitService;
	@Autowired
	private SiteConfig citeConfig;
	
	// Collection
	@RequestMapping(value = "/api/user/collect",method = RequestMethod.GET)
	private Result<PageDataBody> collectList(@RequestParam(value = "name",defaultValue = "1") String name,@RequestParam(value = "p",defaultValue = "1") Integer p){
		User user = userService.findByName(name);
		/*if(user == null) {
			return new Result<PageDataBody>(true, "User does not exists");
		}*/
		ApiAssert.notNull(user, "User does not exists");
		PageDataBody<Topic> page = collectDaoService.page(p, 5, user.getUserId());
		return new Result<PageDataBody>(true, page);
	}
	

	@RequestMapping(value = "/api/user/topic",method = RequestMethod.GET)
	private Result<PageDataBody> topicList(@RequestParam(value = "name",defaultValue = "1") String name,@RequestParam(value = "p",defaultValue = "1") Integer p){
		PageDataBody<Topic> page = topicService.pageByAuthor(p, 5, name);
		return new Result<PageDataBody>(true, page);
	}

	@RequestMapping(value = "/api/user/reply",method = RequestMethod.GET)
	private Result<PageDataBody> replyList(@RequestParam(value = "name",defaultValue = "1") String name,@RequestParam(value = "p",defaultValue = "1") Integer p){
		PageDataBody<ReplyAndTopicByName> page = replyService.findAllByNameAndTopic(name, p, 5);
		return new Result<PageDataBody>(true, page);
	}
	

	@RequestMapping(value = "/api/user/follow/topic",method = RequestMethod.GET)
	private Result<PageDataBody> followList(@RequestParam(value = "uid",defaultValue = "-1") Integer uid,@RequestParam(value = "p",defaultValue = "1") Integer p){
		PageDataBody<Topic> page = followService.pageTopic(p, 5, uid);
		return new Result<PageDataBody>(true, page);
	}
	

	@RequestMapping(value = "/api/user/fans",method = RequestMethod.GET)
	private Result<PageDataBody> fansList(@RequestParam(value = "fid",defaultValue = "-1") Integer fid,@RequestParam(value = "p",defaultValue = "1") Integer p){
		PageDataBody<User> page = followService.followMe(p, 5, fid);
		return new Result<PageDataBody>(true, page);
	}
	
	// Question and Anaswer
	@RequestMapping(value = "/api/user/topic/qna",method = RequestMethod.GET)
	private  Result<PageDataBody> qnaTopicList(@RequestParam(value = "name",defaultValue = "-1") String name,@RequestParam(value = "p",defaultValue = "1") Integer p){
		PageDataBody<Topic> page = topicService.pageAllByPtabAndAuthor(p, 5, "qna", name);
		return new Result<PageDataBody>(true, page);
	}
	
	// number of visits
	@RequestMapping(value = "/api/user/visit",method = RequestMethod.GET)
	private Result<PageDataBody> visitList(@RequestParam(value = "vid",defaultValue = "-1") Integer vid,
										   @RequestParam(value = "p",defaultValue = "1") Integer p){
		PageDataBody<User> page = visitService.page(vid, p, 10);
		return new Result<PageDataBody>(true, page);
	}
	
	@RequestMapping(value = "/api/test",method = RequestMethod.GET)
	private Result<Integer> test(Integer p){
		return new Result<Integer>(true, p);
	}
	

	@RequestMapping(value = "/api/numberOfCountTopicsOrReply",method = RequestMethod.GET)
	private Map<String,Object> numberOfCountTopicsOrReply(String type,String userName){
		Map<String, Object> map = new HashMap<String, Object>();
		if(userName == null) {
			map.put("success", false);
			map.put("msg", "Username cannot be empty");
			return map;
		}else if(type.equals("reply")){
			int countByName = replyService.countByName(userName);
			map.put("success", true);
			map.put("msg", countByName);
			return map;
		}else {
			int countByUserName = topicService.countByUserName(userName);
			map.put("success", true);
			map.put("msg", countByUserName);
			return map;
		}
	}
	
	/**
	 * Top100 points ranks. To encourage user to create topics
	 * @return
	 */
	@RequestMapping(value = "/api/user/top100",method = RequestMethod.GET)
	private Result<List> top100(@RequestParam(value = "limit",defaultValue = "100")Integer limit){
		List<Top100> scores = userService.scores(limit);
		return new Result<List>(true, scores);
	}
	
	// user's information
	@RequestMapping(value = "/api/user/logininfo",method = RequestMethod.GET)
	private Result<Map> LoginInfo(HttpServletRequest request){
		User user = getUser(request);
		HashMap<String,Object> map = new HashMap<>();
		if(user == null) {
			map.put("intro", citeConfig.getIntro());
			return new Result<>(false, map);
		}else {
			map.put("userName", user.getUserName());
			map.put("avatar", user.getAvatar());
			map.put("signature", user.getSignature());
			int countTopic = topicService.countByUserName(user.getUserName());
			int countCollect = collectDaoService.count(user.getUserId());
			int countFollow = followService.countByUid(user.getUserId());
			int countNotReadNotice = noticeService.countNotReadNotice(user.getUserName());
			int countScore = userService.countScore(user.getUserId());
			map.put("countTopic", countTopic);
			map.put("countCollect", countCollect);
			map.put("countFollow", countFollow);
			map.put("countNotReadNotice", countNotReadNotice);
			map.put("countScore", countScore);
			return new Result<Map>(true, map);
		}
	}
	
	// Other topics
	@RequestMapping(value = "/api/user/other/topic",method = RequestMethod.GET)
	private Result<List> otherTopic(String userName,Integer topicId){
		List<Topic> list = topicService.findOther(userName, topicId);
		return new Result<List>(true, list);
	}
}
