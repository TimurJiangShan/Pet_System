package com.example.web.admin;


import com.example.entity.Node;
import com.example.exception.ApiAssert;
import com.example.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dto.PageDataBody;
import com.example.dto.Result;


@Controller
@RequestMapping("/admin/node")
public class NodeAdminController {

	@Autowired
	private NodeService nodeService;
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(@RequestParam(value = "nodeTitle",required = false) String nodeTitle,
					   @RequestParam(value = "p",defaultValue = "1") Integer p,Model model) {
		if(StringUtils.isEmpty(nodeTitle)) nodeTitle = null;
		PageDataBody<Node> page = nodeService.pageForAdmin(nodeTitle, p, 25);
		model.addAttribute("page", page);
		model.addAttribute("nodeTitle", nodeTitle);
		model.addAttribute("p", p);
		return "admin/node/list";
	}
	
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id") Integer id, Model model) {
		Node node = nodeService.findById(id);
		model.addAttribute("node", node);
		return "admin/node/edit";
	}
	
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> edit(Integer nodeId, String nodeTitle, String avatarNormal, String avatarLarge, String nodeDesc, Integer sort) {
		ApiAssert.notNull(nodeId, "Node ID cannot be empty");
		ApiAssert.notEmpty(nodeTitle, "Node Name cannot be empty");
		if(StringUtils.isEmpty(avatarNormal)) avatarNormal = null;
		if(StringUtils.isEmpty(avatarLarge)) avatarLarge = null;
		if(StringUtils.isEmpty(nodeDesc)) nodeDesc = null;
		nodeService.update(nodeId, nodeTitle, avatarNormal, avatarLarge, nodeDesc, sort);
		return new Result<>(true, "Update success");
	}
	

	@RequestMapping(value = "/add",method = RequestMethod.GET)
	public String add() {
		return "admin/node/add";
	}

	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> add(Integer nodeId, String nodeTitle, String avatarNormal, String avatarLarge, String nodeDesc, Integer sort) {
		ApiAssert.notEmpty(nodeTitle, "Node Name cannot be empty");
		if(StringUtils.isEmpty(avatarNormal)) avatarNormal = null;
		if(StringUtils.isEmpty(avatarLarge)) avatarLarge = null;
		if(StringUtils.isEmpty(nodeDesc)) nodeDesc = null;
		nodeService.save(nodeId, nodeTitle, avatarNormal, avatarLarge, nodeDesc, sort);
		return new Result<>(true, "Add Success");
	}

	
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> delete(Integer id){
		ApiAssert.notNull(id, "Node ID cannot be empty");
		nodeService.deleteById(id);
		return new Result<>(true, "Delete success");
	}
	
	@RequestMapping(value = "/add/index",method = RequestMethod.GET)
	@ResponseBody
	public Result<String> addIndex(@RequestParam(value = "id") Integer id, @RequestParam(value = "status") boolean status){
		Node node = nodeService.findById(id);
		if (node == null) {
			return new Result<>(false, "Node does not exists");
		}
		node.setAddIndex(status);
		nodeService.update(node);
		return new Result<>(true, "Success");
	}
	
	@RequestMapping(value = "/add/nav",method = RequestMethod.GET)
	@ResponseBody
	public Result<String> addNav(@RequestParam(value = "id") Integer id, @RequestParam(value = "status") boolean status){
		Node node = nodeService.findById(id);
		if (node == null) {
			return new Result<>(false, "Node does not exists");
		}
		node.setAddNav(status);
		nodeService.update(node);
		return new Result<>(true, "Success");
	}
	
	
	
}
