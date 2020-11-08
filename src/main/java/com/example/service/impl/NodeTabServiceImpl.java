package com.example.service.impl;

import java.util.List;

import com.example.service.NodeTabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.NodeTabDao;
import com.example.entity.NodeTab;

@Service
public class NodeTabServiceImpl implements NodeTabService {

	@Autowired
	private NodeTabDao nodeTabDao;


	@Override
	public List<NodeTab> findAll() {
		return nodeTabDao.selectAll();
	}


	@Override
	public NodeTab findById(Integer nodeTabId) {
		return nodeTabDao.selectById(nodeTabId);
	}


	@Override
	public NodeTab findByCode(String nodeTabCode) {
		return nodeTabDao.selectByCode(nodeTabCode);
	}


	@Override
	public void save(NodeTab nodeTab) {
		nodeTabDao.insert(nodeTab);
	}


	@Override
	public void update(NodeTab nodeTab) {
		nodeTabDao.updateById(nodeTab);
	}


	@Override
	public void deleteById(Integer nodeTabId) {
		nodeTabDao.deleteById(nodeTabId);
	}
	
}
