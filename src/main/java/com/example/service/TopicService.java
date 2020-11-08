package com.example.service;

import java.util.List;

import com.example.dto.PageDataBody;
import com.example.dto.TopicExecution;
import com.example.entity.Topic;
import com.example.entity.User;
import com.example.entity.Tag;

public interface TopicService {


	PageDataBody<Topic> pageByNodeAndNodeTab(Integer pageNumber, Integer pageSize, String nodeTab,String nodeTitle);

	PageDataBody<Topic> pageLike(Integer pageNumber, Integer pageSize, String like);
	

	PageDataBody<Topic> pageAllByTabAndNode(Integer pageNumber, Integer pageSize,String tab, String node);
	

	PageDataBody<Topic> pageAllByNode(Integer pageNumber, Integer pageSize,String nodeCode);

	PageDataBody<Topic> pageAllByPtabAndAuthor(Integer pageNumber, Integer pageSize,String ptab,String author);
	

	PageDataBody<Topic> pageAllNewest(Integer pageNumber, Integer pageSize,String nodeCode);

	PageDataBody<Topic> pageGood(Integer pageNumber, Integer pageSize,String nodeCode);
	

	PageDataBody<Topic> pageNoReply(Integer pageNumber, Integer pageSize,String nodeCode);


	Topic findByTopicId(Integer topicId);
	

	List<Topic> findOtherTopicByAuthor(Integer currentTopicId, String author, Integer limit);
	

	List<Topic> findHot(Integer start,Integer limit);
	

	List<Topic> findTodayNoReply(Integer start,Integer limit);
	

	PageDataBody<Topic> findIndexHot(Integer start,Integer limit,String tab);
	

	PageDataBody<Topic> pageByAuthor(Integer pageNumber, Integer pageSize, String author);
	

	List<Topic> findAll();

	void deleteByTopicId(Integer topicId);
	

	void deleteByAuthor(String author);
	

	void topByTopicId(Integer topicId);

	void goodByTopicId(Integer topicId);
	

	TopicExecution saveTopic(Topic topic);
	
	TopicExecution createTopic(String title, String content, String statusCd, String nodeCode,String nodeTitle,String tag,User user);

	void updateTopic(Topic topic);
	

	void updateTopicAvatar(User user);
    

	void updateNodeTitile(String oldNodeTitle, String newNodeTitle);
	

	PageDataBody<Topic> findCollectsById(Integer pageNumber, Integer pageSize, Integer uid);
	

	int countByUserName(String userName);

	PageDataBody<Tag> findByTag(Integer pageNumber, Integer pageSize);

	PageDataBody<Topic> pageByTag(String tag,Integer pageNumber, Integer pageSize);

    int countAllTopic(String tab, String statusCd);

    int countTopicByNode(String nodeTitle);
    

    List<Topic> findOther(String userName,Integer topicId);
    

    int countToday();
    
    PageDataBody<Topic> pageForAdmin(String author,String startDate,String endDate,Integer pageNumber, Integer pageSize);
    
    int countAllForAdmin(String author,String startDate,String endDate);
    
    Topic findById(Integer id);
}
