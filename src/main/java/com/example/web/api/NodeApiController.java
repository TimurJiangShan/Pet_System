package com.example.web.api;

import com.example.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NodeApiController {
	
	@Autowired
	private NodeService nodeService;

}
