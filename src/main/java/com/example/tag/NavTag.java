package com.example.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.example.entity.Node;
import com.example.service.NodeService;
import com.example.util.ApplicationContextUtil;


public class NavTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) super.getJspContext();
		ServletRequest request = pageContext.getRequest();
		Object nodeName = request.getAttribute("nodeName");
		NodeService nodeService = ApplicationContextUtil.getBean(NodeService.class);
		List<Node> nodes = nodeService.listForNav();
		StringBuilder sb = new StringBuilder();
		sb.append("<div id=\"tab-nav\" style=\"overflow-x: auto;\">");
		sb.append("\t\n");
		sb.append("<div class=\"container\" style=\"height: 45px;\">");
		sb.append("\t\n");
		sb.append("<ul style=\"padding-left: 0px\">");
		sb.append("\t\n");
		if ("index".equals(nodeName)) {
			sb.append("<li class=\"li-cate\"><a href=\"/\" class=\"li-cate-active\" style=\"padding-left: 0px;\">HomePage</a></li>");
		} else {
			sb.append("<li class=\"li-cate\"><a href=\"/\" class=\"li-cate-a\" style=\"padding-left: 0px;\">HomePage</a></li>");
		}
		if ("All".equals(nodeName)) {
			sb.append("<li class=\"li-cate\"><a href=\"/?node=All\" class=\"li-cate-active\">All</a></li>");
		} else {
			sb.append("<li class=\"li-cate\"><a href=\"/?node=All\" class=\"li-cate-a\">All</a></li>");
		}
		sb.append("\t\n");
		for (Node node : nodes) {
			if (node.getNodeTitle().equals(nodeName)) {
				sb.append("<li class=\"li-cate\"><a href=\"/?node="+ node.getNodeTitle() +"\" class=\"li-cate-active\">"+ node.getNodeTitle() +"</a></li>");
			} else {
				sb.append("<li class=\"li-cate\"><a href=\"/?node="+ node.getNodeTitle() +"\" class=\"li-cate-a\">"+ node.getNodeTitle() +"</a></li>");
			}
			sb.append("\t\n");
		}
		sb.append("</ul>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		sb.append("</div>");
		sb.append("\t\n");
		super.getJspContext().getOut().append(sb.toString());
	}
	
}
