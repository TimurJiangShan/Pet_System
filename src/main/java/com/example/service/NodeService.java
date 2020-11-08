package com.example.service;

import java.util.List;

import com.example.entity.Node;

import com.example.dto.PageDataBody;

public interface NodeService {

	List<Node> findAll(Integer pageNumber, Integer pageSize);

	List<Node> findAllByTab(String tabName,Integer pageNumber, Integer pageSize);

	Node findByTitle(String Title);

	Node findById(Integer id);

	List<Node> findChildrenNode(String nodeTitle,Integer pageNumber, Integer pageSize);

	List<Node> adjacencyNode(Node node);
	
	List<Node> listForIndex();
	
	List<Node> listForNav();

	PageDataBody<Node> pageForAdmin(String nodeTitle,Integer pageNumber, Integer pageSize);

	int count(String nodeTitle);

	/**
	 * Update Node
	 * @param nodeId
	 * @param nodeTitle
	 * @param avatarNormal
	 * @param avatarLarge
	 * @param nodeDesc
	 */
	void update(Integer nodeId, String nodeTitle, String avatarNormal, String avatarLarge, String nodeDesc, Integer sort);

	void update(Node node);

	void deleteById(Integer id);

	int countToday();

	/**
	 * @param nodeId
	 * @param nodeTitle
	 * @param avatarNormal
	 * @param avatarLarge
	 * @param nodeDesc
	 */
	void save(Integer nodeId, String nodeTitle, String avatarNormal, String avatarLarge, String nodeDesc, Integer sort);
}
