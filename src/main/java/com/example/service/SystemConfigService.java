package com.example.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.example.entity.SystemConfig;


public interface SystemConfigService {

	SystemConfig getById(Integer id);
	
	SystemConfig getByKey(String key);

	Map<String,Object> getUploadConfig();
	

	List<SystemConfig> getAllList();
	

	List<SystemConfig> getByPid(Integer pid);
	

	Map<String,Object> getAllMap();
	

	List<SystemConfig> getBatchKeys(Collection<? extends Serializable> keys);
	
	List<SystemConfig> edit(Integer pid);
	
	void update(SystemConfig systemConfig);
	
	void update(String key,String value);
	
	void update(List<Map<String, String>> list);
	

	void updateUploadConfig(Integer id);
	
	Integer getAge();
	
	void setAge(Integer age);
}
