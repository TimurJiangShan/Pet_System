package com.example.web.front;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import com.example.base.BaseEntity;
import com.example.entity.Follow;
import com.example.entity.Topic;
import com.example.entity.User;
import com.example.service.CollectService;
import com.example.service.FollowService;
import com.example.service.NoticeService;
import com.example.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dto.PageDataBody;
import com.example.dto.Result;

/**
 * @author Jiangshan
 * 10/11/2020
 */
@Controller
public class FollowController extends BaseController{

	@Autowired
	private FollowService followService;
	@Autowired
	private CollectService collectDaoService;
	@Autowired
	private TopicService rootTopicService;
	@Autowired
	private NoticeService rootNoticeService;
	
	/**
	 * Check if it is followed
	 * @param fid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/follow/isFollow",method = RequestMethod.GET)
	@ResponseBody
	private Result<Integer> isFollow(Integer fid,HttpServletRequest request){
		User user = getUser(request);
		if(user == null) return new Result<>(false, "Unfollow");
		if(user.getUserId() == fid) {
			return new Result<>(false, "The Same user");
		}
		int follow = followService.isFollow(user.getUserId(), fid);
		if(follow == 0) {
			return new Result<>(false, "Unfollow");
		}
		return new Result<>(true, follow);
	}
	
	/**
	 * follow
	 * @param fid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/follow/save",method = RequestMethod.POST)
	@ResponseBody
	private Result<Integer> save(Integer fid,HttpServletRequest request){
		Follow follow = new Follow();
		follow.setUid(getUser(request).getUserId());
		follow.setFid(fid);
		follow.setCreateDate(new Date());
		int insert = followService.insert(follow);
		if(insert == 1) {
			String info = "Follow Success";
			return new Result<Integer>(true,info);
		}
		return new Result<>(false,"Follow Failed");
	};
	
	// Cancel Follow
	@RequestMapping(value = "/follow/delete",method = RequestMethod.POST)
	@ResponseBody
	private Result<Integer> delete(Integer fid,HttpServletRequest request){
		int delete = followService.delete(getUser(request).getUserId(), fid);
		if(delete == 1) {
			String info = "Cancel Follow Success";
			return new Result<Integer>(true,info);
		}
		return new Result<>(false,"Cancel Follow Fails");
	}
	
	/* number of follow	*/
	@RequestMapping(value = "/follow/count/for",method = RequestMethod.GET)
	@ResponseBody
	private Result<Integer> countByUid(Integer uid,HttpServletRequest request){
		int countByUid = followService.countByUid(uid);
		return new Result<Integer>(true,countByUid);
	}
	
	// The number of people who follows me
	@RequestMapping(value = "/follow/count/to",method = RequestMethod.GET)
	@ResponseBody
	private Result<Integer> countByFid(Integer fid,HttpServletRequest request){
		int countByFid = followService.countByFid(fid);
		return new Result<Integer>(true,countByFid);
	}
	
	/**
	 * topics
	 */
	@RequestMapping(value = "/follow/topics",method = RequestMethod.GET)
	private String followTopics(HttpServletRequest request,@RequestParam(value = "p",defaultValue = "1")Integer p) {
		User user = getUser(request);
		if(user == null) return "error-page/404.jsp";
		int countCollect = collectDaoService.count(user.getUserId());// Number of collection
		int countTopicByUserName = rootTopicService.countByUserName(user.getUserName());// Numebr of Topics
		int notReadNotice = rootNoticeService.countNotReadNotice(user.getUserName());// Number of unread message
		PageDataBody<Topic> pageTopic = followService.pageTopic(p, 5, user.getUserId());
		BaseEntity baseEntity = new BaseEntity();
		request.setAttribute("baseEntity", baseEntity);
		request.setAttribute("countCollect", countCollect);
		request.setAttribute("countTopicByUserName", countTopicByUserName);
		request.setAttribute("notReadNotice", notReadNotice);
		request.setAttribute("user", user);
		request.setAttribute("page", pageTopic);
		return "user/follow_topics";
	}
}
