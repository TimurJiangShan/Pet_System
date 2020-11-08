package com.example.service.impl;

import java.util.Date;
import java.util.List;

import com.example.exception.OperationFailedException;
import com.example.service.SystemConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.TopicDao;
import com.example.dao.UserDao;
import com.example.dto.PageDataBody;
import com.example.dto.TopicExecution;
import com.example.entity.Topic;
import com.example.entity.User;
import com.example.entity.Tag;
import com.example.enums.InsertTopicEnum;
import com.example.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService{

	private Logger log = LoggerFactory.getLogger(TopicServiceImpl.class);
	
	@Autowired
	private TopicDao rootTopicDao;
	
	@Autowired
	private UserDao rootUserDao;
	
	@Autowired
	@Qualifier("systemConfigServiceImpl")
	private SystemConfigService systemConfigService;

	@Override
	public PageDataBody<Topic> pageByNodeAndNodeTab(Integer pageNumber, Integer pageSize, String nodeTab,String nodeTitle) {
		if(nodeTab.equals("all")) {
			return pageAllByNode(pageNumber,pageSize,nodeTitle);
		}else if(nodeTab.equals("good")) {
			return pageGood(pageNumber,pageSize,nodeTitle);
		}else if(nodeTab.equals("noReply")) {
			return pageNoReply(pageNumber,pageSize,nodeTitle);
		}else {
			return pageAllNewest(pageNumber,pageSize,nodeTitle);
		}
	}

	@Override
	public PageDataBody<Topic> pageAllByTabAndNode(Integer pageNumber, Integer pageSize,String tab, String node) {
		List<Topic> list = rootTopicDao.selectAllByTabAndNode((pageNumber - 1) * pageSize, pageSize,tab, node);
		int total = rootTopicDao.countTopicByNode(node);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}
	

	@Override
	public PageDataBody<Topic> pageAllByNode(Integer pageNumber, Integer pageSize, String nodeTitle) {
		List<Topic> list = rootTopicDao.selectAllByNode((pageNumber - 1) * pageSize, pageSize,nodeTitle);
		int total = rootTopicDao.countTopicByNode(nodeTitle);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}


	@Override
	public PageDataBody<Topic> pageGood(Integer pageNumber, Integer pageSize,String nodeTitle) {
		List<Topic> list = rootTopicDao.selectAllGood((pageNumber - 1) * pageSize, pageSize,nodeTitle);
		int total = rootTopicDao.countTopicGoodByNode(nodeTitle);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}


	@Override
	public PageDataBody<Topic> pageNoReply(Integer pageNumber, Integer pageSize,String nodeTitle) {
		List<Topic> list = rootTopicDao.selectAllNoReply((pageNumber - 1) * pageSize, pageSize,nodeTitle);
		int total = rootTopicDao.countTopicNoReplyByNode(nodeTitle);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}

	@Override
	public Topic findByTopicId(Integer topicId) {
		return rootTopicDao.selectByTopicId(topicId);
	}


	@Override
	public List<Topic> findOtherTopicByAuthor(Integer currentTopicId, String author, Integer limit) {
		//return rootTopicDao.selectByAuthor(currentTopicId, author, 0, limit);
		return null;
	}

	@Override
	public PageDataBody<Topic> pageByAuthor(Integer pageNumber, Integer pageSize, String author) {
		int totalRow = rootTopicDao.countAllByName(author);
		List<Topic> list = rootTopicDao.selectByAuthor(author, (pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}


	@Override
	public List<Topic> findAll() {
		return rootTopicDao.selectAll();
	}


	@Override
	public void deleteByTopicId(Integer topicId) {
		rootTopicDao.deleteById(topicId);
	}


	@Override
	public void deleteByAuthor(String author) {
		rootTopicDao.deleteByAuthor(author);
	}

	@Override
	public void topByTopicId(Integer topicId) {
		Topic topic = rootTopicDao.selectByTopicId(topicId);
		if(topic != null) {
			topic.setTop(!topic.getTop());
			rootTopicDao.updateByTopicId(topic);
		}
	}

	@Override
	public void goodByTopicId(Integer topicId) {
		Topic topic = rootTopicDao.selectByTopicId(topicId);
		if(topic != null) {
			topic.setGood(!topic.getGood());
			rootTopicDao.updateByTopicId(topic);
		}
	}


	@Transactional
	@Override
	public TopicExecution saveTopic(Topic topic) {
		try {
			int insert = rootTopicDao.insert(topic);
			if(insert <= 0) {
				throw new OperationFailedException("发布话题失败！");
			}else {

				rootUserDao.updateScoreByName(Integer.valueOf(systemConfigService.getByKey("create_topic_score").getValue()), topic.getAuthor());
				// Topic rootTopic = rootTopicDao.selectByTitleAndDate(topic.getTitle(), topic.getCreateDate());
				return new TopicExecution(topic.getTitle(), InsertTopicEnum.SUCCESS, topic);
			}
		}catch (OperationFailedException e1) {
			log.error("post wrong: {}", e1.getMessage());

		}catch (Exception e) {
			log.error("post wrong: {}", e.getMessage());
			// throw new OperationSystemException("insert into RootTopic error "+e.getMessage());
		}
		return null;
		
	}
	
	@Override
	public TopicExecution createTopic(String title, String content, String statusCd, String nodeCode,String nodeTitle, String tag,User user) {
		Topic topic = new Topic();
		topic.setPtab(null);
		topic.setTab(null);
		topic.setTitle(title);
		topic.setTag(tag);
		topic.setContent(content);
		topic.setCreateDate(new Date());
		topic.setUpdateDate(new Date());
		topic.setLastReplyTime(null);
		topic.setLastReplyAuthor(null);
		topic.setViewCount(0);
		topic.setAuthor(user.getUserName());
		topic.setTop(false);
		topic.setGood(false);
		topic.setShowStatus(true);
		topic.setReplyCount(0);
		topic.setIsDelete(false);
		topic.setTagIsCount(true);
		topic.setPostGoodCount(null);
		topic.setPostBadCount(null);
		topic.setStatusCd(statusCd);
		topic.setNodeSlug(nodeCode);
		topic.setNodeTitle(nodeTitle);
		topic.setRemark(null);
		topic.setAvatar(user.getAvatar());//话题作者的头像
		topic.setUrl(null);
		TopicExecution saveTopic = saveTopic(topic);
		return saveTopic;
	}

	@Override
	public void updateTopic(Topic topic) {
		rootTopicDao.updateByTopicId(topic);
	}


	@Override
	public PageDataBody<Topic> findCollectsById(Integer pageNumber, Integer pageSize, Integer uid) {
		return null;
	}


	@Override
	public int countByUserName(String userName) {
		return rootTopicDao.countAllByName(userName);
	}


	@Override
	public PageDataBody<Topic> pageAllNewest(Integer pageNumber, Integer pageSize,String nodeTitle) {
		List<Topic> list = rootTopicDao.selectAllNewest((pageNumber - 1) * pageSize, pageSize,nodeTitle);
		int total = rootTopicDao.countTopicByNode(nodeTitle);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}


	@Override
	public List<Topic> findHot(Integer start, Integer limit) {
		return rootTopicDao.selectHot(start, limit);
	}


	@Override
	public PageDataBody<Tag> findByTag(Integer pageNumber, Integer pageSize) {
		int totalRow = rootTopicDao.countTag();
		List<Tag> list = rootTopicDao.selectAllTag((pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}


	@Override
	public PageDataBody<Topic> pageByTag(String tag, Integer pageNumber, Integer pageSize) {
		int totalRow = rootTopicDao.countByTag(tag);
		List<Topic> list = rootTopicDao.selectByTag(tag, (pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}


	@Override
	public void updateTopicAvatar(User user) {
		rootTopicDao.updateTopicAvatar(user);
	}


	@Override
	public void updateNodeTitile(String oldNodeTitle, String newNodeTitle) {
		rootTopicDao.updateNodeTitile(oldNodeTitle, newNodeTitle);
	}


	@Override
	public int countAllTopic(String tab, String statusCd) {
		return rootTopicDao.countTopicByTabAndStatusCd(tab, statusCd);
	}

	@Override
	public PageDataBody<Topic> pageLike(Integer pageNumber, Integer pageSize, String like) {
		List<Topic> list = rootTopicDao.selectByLike(like, (pageNumber - 1) * pageSize, pageSize);
		int totalRow = rootTopicDao.countLike(like);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}


	@Override
	public PageDataBody<Topic> pageAllByPtabAndAuthor(Integer pageNumber, Integer pageSize, String ptab, String author) {
		int totalRow = rootTopicDao.countAllByNameAndPtab(author, ptab);
		List<Topic> list = rootTopicDao.selectAllByPtabAndAuthor((pageNumber - 1) * pageSize, pageSize, ptab, author);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}


	@Override
	public PageDataBody<Topic> findIndexHot(Integer pageNumber, Integer pageSize, String tab) {
		int totalRow = rootTopicDao.countIndexHot(tab);
		List<Topic> list = rootTopicDao.selectIndexHot((pageNumber - 1) * pageSize, pageSize, tab);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	@Override
	public List<Topic> findTodayNoReply(Integer start, Integer limit) {
		return rootTopicDao.selectTodayNoReply(start, limit);
	}


	@Override
	public List<Topic> findOther(String userName, Integer topicId) {
		return rootTopicDao.selectOther(userName, topicId);
	}

	@Override
	public int countTopicByNode(String nodeTitle) {
		return rootTopicDao.countTopicByNode(nodeTitle);
	}

	@Override
	public int countToday() {
		return rootTopicDao.countToday();
	}

	@Override
	public PageDataBody<Topic> pageForAdmin(String author, String startDate, String endDate, Integer pageNumber,
			Integer pageSize) {
		List<Topic> list = rootTopicDao.selectAllForAdmin(author, startDate, endDate, (pageNumber - 1) * pageSize, pageSize);
		int totalRow = countAllForAdmin(author, startDate, endDate);
		return new PageDataBody<Topic>(list, pageNumber, pageSize, totalRow);
	}

	@Override
	public int countAllForAdmin(String author,String startDate,String endDate) {
		return rootTopicDao.countAllForAdmin(author, startDate, endDate);
	}

	
	@Override
	public Topic findById(Integer id) {
		return rootTopicDao.selectByTopicId(id);
	}

}
