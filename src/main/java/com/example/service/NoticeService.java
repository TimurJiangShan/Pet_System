package com.example.service;

import java.util.List;

import com.example.dto.PageDataBody;
import com.example.entity.Notice;

public interface NoticeService {

	int countNotReadNotice(String author);

	int countByAuthor(String author);

	List<Notice> selectAll(String author);

	PageDataBody<Notice> pageByAuthor(Integer pageNumber, Integer pageSize, String author);

	void updateIsRead(String author);

	void save(Notice notice);

	void deleteByTargetAuthorName(String targetAuthorName);
}
