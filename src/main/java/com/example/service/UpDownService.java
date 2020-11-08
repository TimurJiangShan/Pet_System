package com.example.service;

import com.example.dto.DMLExecution;
import com.example.entity.UpDown;
/**
 * register error
 * @Chao Wang
 * 10/10/2020
 *
 * TODO
 */
public interface UpDownService {


	DMLExecution save(UpDown upDown);
	

	DMLExecution update(UpDown upDown);
	

	int countUpOrDown(Integer tid,Integer upDown);
	
}
