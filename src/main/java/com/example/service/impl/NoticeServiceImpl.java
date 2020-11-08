package com.example.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.NoticeDao;
import com.example.dto.PageDataBody;
import com.example.entity.Notice;
import com.example.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeDao rootNoticeDao;


	@Override
	public int countNotReadNotice(String author) {
		return rootNoticeDao.countNotReadByAuthor(author);
	}

	@Override
	public List<Notice> selectAll(String author) {
		return rootNoticeDao.selectAll(author);
	}


	@Override
	public PageDataBody<Notice> pageByAuthor(Integer pageNumber, Integer pageSize, String author) {
		int totalRow = rootNoticeDao.countByAuthor(author);
		List<Notice> list = rootNoticeDao.selectAll(author, (pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}


	@Override
	public void updateIsRead(String author) {
		rootNoticeDao.updateByAuthorName(author);
	}


	@Override
	public void save(Notice notice) {
		rootNoticeDao.insert(notice);
	}

	@Override
	public int countByAuthor(String author) {
		return rootNoticeDao.countByAuthor(author);
	}


	@Override
	public void deleteByTargetAuthorName(String targetAuthorName) {
		rootNoticeDao.deleteByTargetAuthorName(targetAuthorName);
	}
	
}
