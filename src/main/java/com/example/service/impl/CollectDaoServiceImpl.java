package com.example.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CollectDao;
import com.example.dto.PageDataBody;
import com.example.entity.Collect;
import com.example.entity.Topic;
import com.example.service.CollectService;

@Service
public class CollectDaoServiceImpl implements CollectService{

	@Autowired
	private CollectDao collectDao;

	@Override
	public PageDataBody<Topic> page(Integer pageNumber, Integer pageSize,Integer uid) {
		int total = collectDao.count(uid);
		List<Topic> list = collectDao.selectAllByCollect((pageNumber - 1) * pageSize, pageSize, uid);
		return new PageDataBody<>(list, pageNumber, pageSize, total);
	}


	@Override
	public int insert(Collect collect) {
		return collectDao.insert(collect);
	}


	@Override
	public int delete(Integer uid, Integer tid) {
		return collectDao.delete(uid, tid);
	}


	@Override
	public void deleteByUid(Integer uid) {
		collectDao.deleteByUid(uid);
	}


	@Override
	public int count(Integer uid) {
		return collectDao.count(uid);
	}

	@Override
	public int isCollect(Integer uid, Integer tid) {
		return collectDao.isCollect(uid, tid);
	}


	@Override
	public int countByTid(Integer tid) {
		return collectDao.countByTid(tid);
	}

}
