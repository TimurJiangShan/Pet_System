package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.FollowDao;
import com.example.dto.PageDataBody;
import com.example.entity.Follow;
import com.example.entity.Topic;
import com.example.entity.User;
import com.example.service.FollowService;

@Service
public class FollowServiceImpl implements FollowService{

	@Autowired
	private FollowDao followDao;

	@Override
	public PageDataBody<User> page(Integer pageNumber, Integer pageSize, Integer uid) {
		int total = followDao.countByUid(uid);
		List<User> list = followDao.select((pageNumber - 1) * pageSize, pageSize, uid);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}

	@Override
	public int insert(Follow follow) {
		return followDao.insert(follow);
	}

	@Override
	public int delete(Integer uid, Integer fid) {
		return followDao.delete(uid, fid);
	}


	@Override
	public int countByUid(Integer uid) {
		return followDao.countByUid(uid);
	}



	@Override
	public int countByFid(Integer fid) {
		return followDao.countByFid(fid);
	}



	@Override
	public int isFollow(Integer uid, Integer fid) {
		return followDao.isFollow(uid, fid);
	}


	@Override
	public PageDataBody<User> followMe(Integer pageNumber, Integer pageSize, Integer fid) {
		int total = followDao.countByFid(fid);
		List<User> list = followDao.selectByFid((pageNumber - 1) * pageSize, pageSize, fid);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}



	@Override
	public PageDataBody<Topic> pageTopic(Integer pageNumber, Integer pageSize, Integer uid) {
		int total = followDao.countTopic(uid);
		List<Topic> list = followDao.selectTopic((pageNumber - 1) * pageSize, pageSize, uid);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}

}
