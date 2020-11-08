package com.example.service;

import java.util.List;
import java.util.Map;
import com.example.dto.PageDataBody;
import com.example.dto.ReplyExecution;
import com.example.entity.ReplyAndTopicByName;
import com.example.entity.Reply;

public interface ReplyService {

	Reply findById(Integer id);

	List<Reply> findAll();

	PageDataBody<Reply> findAll(Integer pageNumber, Integer pageSize);

	PageDataBody<ReplyAndTopicByName> findAllByNameAndTopic(String replyAuthorName,Integer pageNumber, Integer pageSize);

	List<Reply> findByTopicId(Integer topicId);

	PageDataBody<Reply> page(Integer pageNumber, Integer pageSize, Integer topicId);

	ReplyExecution save(Reply reply);

	void deleteByReplyId(Integer replyId);

	void deleteByTopicId(Integer topicId);

	void deleteByReplyAuthorName(String replyAuthorName);

	void update(Reply reply);

	int countAll();

	int countByName(String name);

	int countToday();
	
	/**
	 * Backend Reply List
	 * @param author
	 * @param topic
	 * @param startDate
	 * @param endDate
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	PageDataBody<Map<String,Object>> pageForAdmin(String author,String topic,String startDate,String endDate,Integer pageNumber, Integer pageSize);
    
	/**
	 * Count Backend Reply
	 * @param author
	 * @param topic
	 * @param startDate
	 * @param endDate
	 * @return
	 */
    int countAllForAdmin(String author,String topic,String startDate,String endDate);
}
