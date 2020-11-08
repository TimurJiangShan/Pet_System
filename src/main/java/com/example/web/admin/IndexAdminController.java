package com.example.web.admin;

import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.dto.TopicExecution;
import com.example.entity.AdminUser;
import com.example.entity.User;
import com.example.exception.ApiAssert;
import com.example.service.*;
import com.google.common.base.Joiner;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.dto.Result;
import com.sun.management.OperatingSystemMXBean;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/admin")
public class IndexAdminController {

    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private UserService userService;
    @Autowired
    private NodeService nodeService;

    @Autowired
    private MailService mailService;

    // backend Homepage
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("topic_count", topicService.countToday());
        model.addAttribute("comment_count", replyService.countToday());
        model.addAttribute("user_count", userService.countToday());
        model.addAttribute("node_count", nodeService.countToday());
        model.addAttribute("os_name", System.getProperty("os.name"));
        int kb = 1024;
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();
        float totalMemorySize = (float) osmxb.getTotalPhysicalMemorySize() / kb / kb / kb;

        float usedMemory = (float) (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / kb / kb / kb;
        double systemCpuLoad = osmxb.getSystemCpuLoad();
        double processCpuLoad = osmxb.getProcessCpuLoad();

        DecimalFormat df = new DecimalFormat("0.0");
        model.addAttribute("totalMemorySize", df.format(totalMemorySize));
        model.addAttribute("usedMemory", df.format(usedMemory));
        model.addAttribute("systemCpuLoad", df.format(systemCpuLoad));
        model.addAttribute("processCpuLoad", df.format(processCpuLoad));

        return "/admin/index";
    }

    // Backend Login
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "redirect:/admin/index";
        }
        return "/admin/login";
    }

    // Backend process
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, @RequestParam(defaultValue = "0") Boolean rememberMe, Model model) {
        try {
            // Add user auth
            Subject subject = SecurityUtils.getSubject();
            if (!subject.isAuthenticated()) {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
                // Auth and catch error
                subject.login(token);
            }
        } catch (AuthenticationException e) {
            model.addAttribute("error", "User or password wrong");
            model.addAttribute("username", username);
            return "/admin/login";
        }
        return "redirect:/admin/index";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return "admin/error/error";
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)

    @ResponseBody
    public Result<AdminUser> getAdminUser() {
        AdminUser user = (AdminUser) SecurityUtils.getSubject().getPrincipal();
        return new Result<AdminUser>(true, user);
    }

    @RequestMapping("/email")
    public String email() {
        return "admin/email";
    }


    @RequestMapping(value = "/email/send", method = RequestMethod.POST)
    @ResponseBody
    private Result<Object> save(String title, String content, HttpServletRequest request) {
        List<String> emailList = userService.findAllEmail();
        try {
            String to = Joiner.on(",").join(emailList);
            mailService.sendMail(to, title, content);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(false, "Send Fails");
        }
        return new Result<Object>(true, "Send Success");
    }
}
