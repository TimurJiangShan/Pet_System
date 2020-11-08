package com.example.service;

import com.example.dto.DMLExecution;
import com.example.dto.PageDataBody;
import com.example.entity.User;
import com.example.entity.Visit;


public interface VisitService {


	PageDataBody<User> page(Integer vid,Integer pageNumber,Integer pageSize);
	

	DMLExecution save(Visit visit);
	

	int count(Integer vid);
	
}
