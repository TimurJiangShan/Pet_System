package com.example.service;

import com.example.dto.PageDataBody;
import com.example.entity.Follow;
import com.example.entity.Topic;
import com.example.entity.User;

public interface FollowService {

	/**
	 * People who I follow
	 * @param pageNumber
	 * @param pageSize
	 * @param tab
	 * @return
	 */
	PageDataBody<User> page(Integer pageNumber, Integer pageSize,Integer uid);
	
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @param uid
	 * @return
	 */
	PageDataBody<Topic> pageTopic(Integer pageNumber,Integer pageSize,Integer uid);
	
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @param tab
	 * @return
	 */
	PageDataBody<User> followMe(Integer pageNumber, Integer pageSize,Integer fid);

	int insert(Follow follow);

	int delete(Integer uid,Integer fid);

	int countByUid(Integer uid);

	int countByFid(Integer fid);

	int isFollow(Integer uid,Integer fid);
}
