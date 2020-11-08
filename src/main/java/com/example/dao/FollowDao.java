package com.example.dao;

import java.util.List;

import com.example.entity.Follow;
import com.example.entity.Topic;
import com.example.entity.User;
import org.apache.ibatis.annotations.Param;

public interface FollowDao {


	List<User> select(@Param("start")Integer start, @Param("limit")Integer limit, @Param("uid")Integer uid);

	List<Topic> selectTopic(@Param("start")Integer start, @Param("limit")Integer limit, @Param("uid")Integer uid);

	List<User> selectByFid(@Param("start")Integer start,@Param("limit")Integer limit,@Param("fid")Integer fid);

	int insert(Follow follow);

	int delete(@Param("uid")Integer uid,@Param("fid")Integer fid);

	int countByUid(@Param("uid")Integer uid);

	int countByFid(@Param("fid")Integer fid);
	
	/**
	 * Check if it follows 0：否 1：是
	 */
	int isFollow(@Param("uid")Integer uid,@Param("fid")Integer fid);
	
	/**
	 * number of user who follows
	 */
	int countTopic(@Param("uid")Integer uid);
}
