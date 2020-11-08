package com.example.service;

import java.util.List;
import com.example.entity.NodeTab;

public interface NodeTabService {

	List<NodeTab> findAll();

	NodeTab findById(Integer nodeTabId);

	NodeTab findByCode(String nodeTabCode);

	void save(NodeTab nodeTab);

	void update(NodeTab nodeTab);
	
	void deleteById(Integer nodeTabId);
}
