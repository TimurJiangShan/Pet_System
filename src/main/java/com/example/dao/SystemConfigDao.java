package com.example.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.example.entity.SystemConfig;
import org.apache.ibatis.annotations.Param;

/**
 * @author Jiangshan Zhao
 * @date 10/10/2020
 */
public interface SystemConfigDao {

	SystemConfig selectById(@Param("id") Integer id);
	
	SystemConfig selectByKey(@Param("key") String key);
	
	List<SystemConfig> selectAll();
	
	List<SystemConfig> selectByPid(@Param("pid") Integer pid);
	
	List<SystemConfig> selectBatchKeys(@Param("keys") Collection<? extends Serializable> keys);
	
	/**
	 * 根据key更新value
	 * @param systemConfig
	 * @return
	 */
	int update(SystemConfig systemConfig);
}
