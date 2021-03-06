package com.example.web.admin;

import java.util.Date;
import java.util.Map;

import com.example.entity.Reply;
import com.example.service.ReplyService;
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
@RequestMapping("/admin/reply")
public class ReplyAdminController {

	@Autowired
	private ReplyService replyService;
	
	/**
	 *
	 * @param author
	 * @param topic
	 * @param startDate
	 * @param endDate
	 * @param p: page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(@RequestParam(value = "author",required = false) String author,
					   @RequestParam(value = "topic",required = false) String topic,
					   @RequestParam(value = "startDate",required = false) String startDate,
					   @RequestParam(value = "endDate",required = false) String endDate,
					   @RequestParam(value = "p",required = false,defaultValue = "1") Integer p,Model model) {
	    if (StringUtils.isEmpty(author)) author = null;
	    if (StringUtils.isEmpty(topic)) topic = null;
	    if (StringUtils.isEmpty(startDate)) startDate = null;
	    if (StringUtils.isEmpty(endDate)) endDate = null;
	    PageDataBody<Map<String, Object>> page = replyService.pageForAdmin(author, topic, startDate, endDate, p, 25);
	    model.addAttribute("page", page);
	    model.addAttribute("author", author);
	    model.addAttribute("topic", topic);
	    model.addAttribute("startDate", startDate);
	    model.addAttribute("endDate", endDate);
	    model.addAttribute("p", p);
	    return "admin/reply/list";
	}

	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public String edit(@RequestParam(value = "id") Integer id,Model model) {
		Reply reply = replyService.findById(id);
		model.addAttribute("reply", reply);
		model.addAttribute("vEnter", "\n");
		return "admin/reply/edit";
	}

	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> edit(@RequestParam(value = "id") Integer id,
							   @RequestParam(value = "content") String content){
		Reply reply = replyService.findById(id);
		reply.setReplyContent(content);
		reply.setUpdateDate(new Date());
		replyService.update(reply);
		return new Result<>(true, "Edit Success！");
	}
	

	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@ResponseBody
	public Result<String> delete(@RequestParam(value = "id") Integer id){
		replyService.deleteByReplyId(id);
		return new Result<>(true, "Delete Success！");
	}
}
