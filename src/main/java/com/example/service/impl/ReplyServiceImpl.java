package com.example.service.impl;

import java.util.List;
import java.util.Map;

import com.example.exception.OperationFailedException;
import com.example.exception.OperationRepeaException;
import com.example.exception.OperationSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.dao.ReplyDao;
import com.example.dao.UserDao;
import com.example.dto.PageDataBody;
import com.example.dto.ReplyExecution;
import com.example.entity.ReplyAndTopicByName;
import com.example.entity.Topic;
import com.example.entity.Reply;
import com.example.enums.InsertReplyEnum;
import com.example.service.ReplyService;
import com.example.service.SystemConfigService;
import com.example.service.TopicService;

@Service
public class ReplyServiceImpl implements ReplyService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ReplyDao replyDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	@Qualifier("systemConfigServiceImpl")
	private SystemConfigService systemConfigService;
	

	@Override
	public Reply findById(Integer id) {
		return replyDao.selectByReplyId(id);
	}


	@Override
	public List<Reply> findAll() {
		return replyDao.selectAll();
	}


	@Override
	public PageDataBody<Reply> findAll(Integer pageNumber, Integer pageSize) {
		int totalRow = replyDao.countAll();
		List<Reply> list = replyDao.selectAll((pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	@Override
	public List<Reply> findByTopicId(Integer topicId) {
		return replyDao.selectByTopicId(topicId);
	}

	@Override
	public PageDataBody<Reply> page(Integer pageNumber, Integer pageSize, Integer topicId) {
		int totalRow = replyDao.countByTopicId(topicId);
		List<Reply> list = replyDao.selectByTopicId((pageNumber - 1) * pageSize, pageSize, topicId);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}
	
	@Transactional
	@Override
	public ReplyExecution save(Reply reply) {
		try {
			if(reply.getReplyContent() == null) {
				throw new OperationRepeaException("comment not none");
			}
			int insert = replyDao.insert(reply);
			if(insert <= 0) {
				throw new OperationFailedException("comment failure！");
			}else {

				userDao.updateScore(Integer.valueOf(systemConfigService.getByKey("create_reply_score").getValue()), reply.getReplyAuthorId());
				return new ReplyExecution(reply.getReplyAuthorName(), InsertReplyEnum.SUCCESS, reply);
						
			}
		} catch (OperationRepeaException e1) {
			throw e1;
		} catch (OperationFailedException e2) {
			throw e2;
		} catch (Exception e) {
			throw new OperationSystemException("insert into RootReply error "+e.getMessage());
		}
	}


	@Override
	public void deleteByReplyId(Integer replyId) {
		Reply reply = findById(replyId);
		Topic topic = topicService.findById(reply.getTopicId());

		topic.setReplyCount(topic.getReplyCount() - 1);

		topicService.updateTopic(topic);

		replyDao.deleteByReplyId(replyId);
	}


	@Override
	public void deleteByTopicId(Integer topicId) {
		replyDao.deleteByTopicId(topicId);
	}


	@Override
	public void deleteByReplyAuthorName(String replyAuthorName) {
		replyDao.deleteByReplyAuthorName(replyAuthorName);
	}


	@Override
	public void update(Reply reply) {
		replyDao.update(reply);
	}


	@Override
	public PageDataBody<ReplyAndTopicByName> findAllByNameAndTopic(String replyAuthorName, Integer pageNumber, Integer pageSize) {
		int totalRow = replyDao.countByName(replyAuthorName);
		List<ReplyAndTopicByName> list = replyDao.selectAllByNameAndTopic(replyAuthorName, (pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}


	@Override
	public int countAll() {
		return replyDao.countAll();
	}

	@Override
	public int countByName(String name) {
		return replyDao.countByName(name);
	}

	@Override
	public int countToday() {
		return replyDao.countToday();
	}

	@Override
	public PageDataBody<Map<String, Object>> pageForAdmin(String author, String topic, String startDate, String endDate,
			Integer pageNumber, Integer pageSize) {
		List<Map<String,Object>> list = replyDao.selectAllForAdmin(author, topic, startDate, endDate, (pageNumber - 1) * pageSize, pageSize);
		int totalRow = countAllForAdmin(author, topic, startDate, endDate);
		return new PageDataBody<Map<String, Object>>(list, pageNumber, pageSize, totalRow);
	}


	@Override
	public int countAllForAdmin(String author, String topic, String startDate, String endDate) {
		return replyDao.countAllForAdmin(author, topic, startDate, endDate);
	}
}
