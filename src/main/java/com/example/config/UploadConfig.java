package com.example.config;

import java.util.List;

import com.example.entity.SystemConfig;
import com.example.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;


public class UploadConfig {

	
	private SystemConfigService systemConfigService;

	private String uploadType;

	private String defaultUploadTopicFiledir;

	private String defaultUploadNodeFiledir;

	private String defaultUploadTagFiledir;

	private String defaultUploadUserFiledir;

	private String defaultUploadStaticUrl;
	
	
	//resouce path
	private String[] resourceLocations;
	
	@Autowired
	public UploadConfig(SystemConfigService systemConfigService) {
		this.systemConfigService = systemConfigService;
		List<SystemConfig> list = this.systemConfigService.getAllList();
	}
	
	public String[] getResourceLocations() {
		return resourceLocations;
	}

	public void setResourceLocations(String[] resourceLocations) {
		this.resourceLocations = resourceLocations;
	}
	
}
