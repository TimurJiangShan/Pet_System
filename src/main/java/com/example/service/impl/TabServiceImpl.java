package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.example.dao.TabDao;
import com.example.entity.Tab;
import com.example.service.TabService;

@Service
public class TabServiceImpl implements TabService{

	@Autowired
	private TabDao tabDao;


	@Override
	public List<Tab> selectAll() {
		return tabDao.selectAll();
	}

}
