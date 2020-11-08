package com.example.service.impl;

import java.util.List;

import com.example.exception.OperationFailedException;
import com.example.exception.OperationRepeaException;
import com.example.exception.OperationSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.dao.VisitDao;
import com.example.dto.DMLExecution;
import com.example.dto.PageDataBody;
import com.example.entity.User;
import com.example.entity.Visit;
import com.example.enums.InsertEnum;
import com.example.enums.UpdateEnum;
import com.example.service.VisitService;


@Service
public class VisitServiceImpl implements VisitService {

	@Autowired
	private VisitDao visitDao;


	@Override
	public PageDataBody<User> page(Integer vid, Integer pageNumber, Integer pageSize) {
		int totalRow = visitDao.count(vid);
		List<User> list = visitDao.select(vid, (pageNumber - 1) * pageSize, pageSize);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}


	@Override
	@Transactional
	public DMLExecution save(Visit visit) {
		try {
			if (visit.getUid() == visit.getVid()) {
				throw new OperationRepeaException("The visitor and the interviewee are the same user！");
			}
			int isVisit = visitDao.isVisit(visit.getUid(), visit.getVid());
			if (isVisit == 0) {
				int insert = visitDao.insert(visit);
				if (insert <= 0) {
					throw new OperationFailedException("add failure!");
				} else {
					return new DMLExecution("Add access record", InsertEnum.SUCCESS, visit);
				}
			} else {
				int update = visitDao.update(visit);
				if (update <= 0) {
					throw new OperationFailedException("update failure!");
				} else {
					return new DMLExecution("Update access records", UpdateEnum.SUCCESS, visit);
				}
			}
		} catch (OperationRepeaException e2) {
			throw e2;
		} catch (OperationFailedException e1) {
			throw e1;
		} catch (Exception e) {
			throw new OperationSystemException("insert into or update visit error " + e.getMessage());
		}
	}

	@Override
	public int count(Integer vid) {
		return visitDao.count(vid);
	}

}
