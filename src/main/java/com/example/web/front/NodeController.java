package com.example.web.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.entity.Node;
import com.example.entity.NodeTab;
import com.example.entity.Topic;
import com.example.exception.ApiAssert;
import com.example.service.NodeService;
import com.example.service.NodeTabService;
import com.example.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dto.PageDataBody;
import com.example.dto.Result;

@Controller
public class NodeController {
	
	@Autowired
	private NodeService nodeService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private NodeTabService nodeTabService;

	@RequestMapping(value = "/node/tab/{tabName}",method = RequestMethod.GET)
	@ResponseBody
	private Result<List> nodeByTab(@PathVariable String tabName){
		ApiAssert.notNull(tabName, "Node cannot be empty");
		List<Node> list = nodeService.findAllByTab(tabName, null, null);
		return new Result<List>(true, list);
	}
	
	@RequestMapping(value = "/n/{name}",method = RequestMethod.GET)
	private String toNode(@PathVariable String name,
						  @RequestParam(value = "s", defaultValue = "all") String nodeTab,
						  @RequestParam(value = "p", defaultValue = "1") Integer p,
						  HttpServletRequest request,HttpServletResponse response) {
		Node node = nodeService.findByTitle(name);
		if(node == null) {
			throw new RuntimeException("Node does not exists， return > <a href='/'>HomePage<a/>");
		}
		List<NodeTab> nodeTabList = nodeTabService.findAll();
		PageDataBody<Topic> page = topicService.pageByNodeAndNodeTab(p, 20, nodeTab, name);
		Node parentNode = nodeService.findByTitle(node.getParentNodeCode());
		List<Node> adjacencyNode = nodeService.adjacencyNode(node);
		List<Node> childrenNode = nodeService.findChildrenNode(node.getNodeTitle(), null, null);
		int countTopicByNode = topicService.countTopicByNode(name);
		request.setAttribute("nodeTabList", nodeTabList);
		request.setAttribute("page", page);
		request.setAttribute("node", node);
		request.setAttribute("nodeTab", nodeTab);
		request.setAttribute("parentNode", parentNode);
		request.setAttribute("adjacencyNode", adjacencyNode);
		request.setAttribute("childrenNode", childrenNode);
		request.setAttribute("countTopicByNode", countTopicByNode);
		return "node/detail";
	}
	
	@RequestMapping(value = "/nodes")
	private String nodes(HttpServletRequest request) {
		List<Node> nodeList = nodeService.findAll(null, null);
		request.setAttribute("nodeList", nodeList);
		return "node/node";
	}
}
