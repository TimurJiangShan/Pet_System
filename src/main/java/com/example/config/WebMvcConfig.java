package com.example.config;

import java.util.Map;

import com.example.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Autowired
	@Qualifier("uploadConfig")
	private SystemConfigService systemConfigService;
	
	//static mapping
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		Map<String, Object> maps = systemConfigService.getUploadConfig();

		String uploadType = (String)maps.get("upload_type");
		if(!uploadType.equals("45")) {

			String staticUrl = (String)maps.get("static_url");

			String uploadFiledir = (String)maps.get("default_upload_filedir") != null ? (String)maps.get("default_upload_filedir") : (String)maps.get("local_upload_filedir");
			String[] locations = {
					uploadFiledir + "topic/",
					uploadFiledir + "node/",
					uploadFiledir + "user/",
					uploadFiledir + "tag/",
					uploadFiledir + "admin-user/"
					};
			//add url
			ResourceHandlerRegistration addResourceLocations = registry.addResourceHandler(staticUrl);
			for(String location : locations) {
				addResourceLocations.addResourceLocations(location);
			}
		}
	}
}
