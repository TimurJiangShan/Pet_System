package com.example.web.admin;

import java.util.List;
import java.util.Map;

import com.example.entity.SystemConfig;
import com.example.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.dto.Result;


@Controller
@RequestMapping("/admin/system")
public class SystemConfigAdminController {

	@Autowired
	@Qualifier("systemConfigServiceImpl")
	private SystemConfigService systemConfigService;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(value = "pid", defaultValue = "1") Integer pid, @RequestParam(value = "index", defaultValue = "0") Integer index, Model model) {
		SystemConfig systemConfig = systemConfigService.getById(pid);
		List<SystemConfig> systemConfigs = systemConfigService.getByPid(pid);
		model.addAttribute("systemConfig", systemConfig);
		model.addAttribute("systemConfigs", systemConfigs);
		model.addAttribute("index", index);
		return "admin/system/edit";
	}

	@RequestMapping(value = "/upload/list", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<SystemConfig>> list(Integer pid) {
		List<SystemConfig> systemConfigs = systemConfigService.getByPid(pid);
		systemConfigs.forEach(systemConfig -> {
			if(systemConfig.getKey().equals("accessKeyId") || systemConfig.getKey().equals("accessKeySecret")) {
				systemConfig.setValue("******");
			}
		});
		return new Result<>(true, systemConfigs);

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> edit(@RequestBody List<Map<String, String>> list) {
		systemConfigService.update(list);
		return new Result<>(true, "success");
	}
}
