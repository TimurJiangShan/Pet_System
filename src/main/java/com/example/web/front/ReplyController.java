package com.example.web.front;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import com.example.entity.Notice;
import com.example.entity.Reply;
import com.example.entity.Topic;
import com.example.entity.User;
import com.example.service.NoticeService;
import com.example.service.ReplyService;
import com.example.service.TopicService;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dto.Result;
import com.example.dto.ReplyExecution;

@Controller
public class ReplyController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ReplyService rootReplyService;
	@Autowired
	private NoticeService rootNoticeService;
	@Autowired
	private TopicService rootTopicService;
	@Autowired
	private UserService rootUserService;
	
	@RequestMapping(value = "/reply/save", method = RequestMethod.POST)
	@ResponseBody
	private Result<ReplyExecution> save(HttpServletRequest request, 
			@RequestParam("topicId") Integer topicId,
			@RequestParam("content") String content){
			User user = getUser(request);
			Reply reply = new Reply();
			reply.setTopicId(topicId);
			reply.setReplyContent(content);
			reply.setCreateDate(new Date());
			reply.setUpdateDate(new Date());
			reply.setReplyAuthorId(user.getUserId());
			reply.setReplyAuthorName(user.getUserName());
			reply.setIsDelete(false);
			reply.setIsRead(false);
			reply.setIsShow(false);
			reply.setReplyGoodCount(0);
			reply.setReplyBadCount(0);
			reply.setReplyType(null);
			reply.setReplyReadCount(0);
			reply.setStatusCd("1000");
			ReplyExecution save = rootReplyService.save(reply);
			Topic findByTopicId = rootTopicService.findByTopicId(topicId);
			findByTopicId.setReplyCount(findByTopicId.getReplyCount()+1);
			findByTopicId.setLastReplyAuthor(user.getUserName());
			findByTopicId.setLastReplyTime(new Date());
			rootTopicService.updateTopic(findByTopicId);

			//回复者与话题作者不是同一个人的时候发送通知
			if(!user.getUserName().equals(findByTopicId.getAuthor())) {
				Notice notice = new Notice();
				notice.setIsRead(false);
				notice.setNoticeAuthorId(user.getUserId());
				notice.setNoticeAuthorName(user.getUserName());
				notice.setTargetAuthorName(findByTopicId.getAuthor());
				notice.setCreateDate(new Date());
				notice.setUpdateDate(new Date());
				notice.setNoticeAction("reply");
				notice.setTopicId(findByTopicId.getTopicId());
				notice.setNoticeContent(content);
				notice.setStatusCd("1000");
				rootNoticeService.save(notice);
			}
			return new Result<ReplyExecution>(true, save);
	}
}
