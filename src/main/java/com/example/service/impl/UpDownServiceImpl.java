package com.example.service.impl;

import com.example.exception.OperationFailedException;
import com.example.exception.OperationSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.dao.UpDownDao;
import com.example.dto.DMLExecution;
import com.example.entity.UpDown;
import com.example.enums.InsertEnum;
import com.example.enums.UpdateEnum;
import com.example.service.UpDownService;

/**
 * register error
 * @Chao Wang
 * 10/10/2020
 *
 * TODO
 */
@Service
public class UpDownServiceImpl implements UpDownService {

	@Autowired
	private UpDownDao upDownDao;

	/**
	 * add record of like or dislike
	 */
	@Override
	@Transactional
	public DMLExecution save(UpDown upDown) {
		try {
			int upOrDown = upDownDao.isUpOrDown(upDown.getUid(), upDown.getTid());
			if (upOrDown == 0) {
				int insert = upDownDao.insert(upDown);
				if (insert <= 0) {
					throw new OperationFailedException("add failure!");
				} else {
					return new DMLExecution(upDown.isUpDown() ? "add like" : "add disliek", InsertEnum.SUCCESS, upDown);
				}
			} else {
				update(upDown);
				return new DMLExecution(upDown.isUpDown() ? "update like " : "update dislike", UpdateEnum.SUCCESS, upDown);
			}
		} catch (OperationFailedException e1) {
			throw e1;
		} catch (Exception e) {
			throw new OperationSystemException("insert into up_down error " + e.getMessage());
		}
	}

	/**
	 * update
	 */
	@Override
	@Transactional
	public DMLExecution update(UpDown upDown) {
		try {
			int update = upDownDao.update(upDown);
			if (update <= 0) {
				throw new OperationFailedException("update failure!");
			} else {
				return new DMLExecution(upDown.isUpDown() ? "update like recorde" : "update dislike recorde", UpdateEnum.SUCCESS, upDown);
			}
		} catch (OperationFailedException e1) {
			throw e1;
		} catch (Exception e) {
			throw new OperationSystemException("update up_down error " + e.getMessage());
		}
	}

	/**
	 * statistic like or dislike
	 */
	@Override
	public int countUpOrDown(Integer tid, Integer upDown) {
		return upDownDao.countUpOrDown(tid, upDown);
	}

}
