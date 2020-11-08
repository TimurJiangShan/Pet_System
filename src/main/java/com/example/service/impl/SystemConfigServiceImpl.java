package com.example.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.dao.SystemConfigDao;
import com.example.entity.SystemConfig;
import com.example.service.SystemConfigService;



@Service
public class SystemConfigServiceImpl implements SystemConfigService {

    private Logger log = LoggerFactory.getLogger(SystemConfigService.class);

    @Autowired
    private SystemConfigDao systemConfigDao;


    private Map<String, Object> uploadConfig;

    private Integer age;

    static {
        System.out.println("SystemConfigServiceImpl initial");
    }


    public SystemConfigServiceImpl() {
        System.out.println("SystemConfigServiceImpl initial");
    }


    @Override
    public Map<String, Object> getAllMap() {
        Map<String, Object> map = null;

        if (map != null) {
            log.debug("get system setting from redis");
            return map;
        } else {
            Map<String, Object> map2 = new LinkedHashMap<>();


            List<SystemConfig> systemConfigs = getAllList();


            List<SystemConfig> systemConfigP = systemConfigs.stream()
                    .filter(systemConfig -> systemConfig.getPid() == 0)
                    .collect(Collectors.toList());

            systemConfigP.forEach(p -> {

                List<SystemConfig> systemConfigC = systemConfigs.stream()
                        .filter(systemConfig -> systemConfig.getPid().equals(p.getSystemConfigId()))
                        .collect(Collectors.toList());
                map2.put(p.getDescription(), systemConfigC);
            });

            log.debug("get system setting from db");
            return map2;
        }
    }

    @Override
    public List<SystemConfig> getAllList() {
        List<SystemConfig> systemConfigs = null;

        if (systemConfigs != null) {
            log.debug("get system setting from redis");
            return systemConfigs;
        } else {
            systemConfigs = systemConfigDao.selectAll();
            return systemConfigDao.selectAll();
        }

    }

    @Override
    public SystemConfig getByKey(String key) {
        return systemConfigDao.selectByKey(key);
    }

    @Override
    public List<SystemConfig> getBatchKeys(Collection<? extends Serializable> keys) {
        return systemConfigDao.selectBatchKeys(keys);
    }

    @Override
    public List<SystemConfig> getByPid(Integer pid) {
        return systemConfigDao.selectByPid(pid);
    }


    @Override
    public List<SystemConfig> edit(Integer pid) {

        return null;
    }

    @Override
    public SystemConfig getById(Integer id) {
        return systemConfigDao.selectById(id);
    }

    @Override
    public void update(SystemConfig systemConfig) {
        systemConfigDao.update(systemConfig);
    }

    @Override
    public void update(String key, String value) {
        SystemConfig systemConfig = new SystemConfig();
        systemConfig.setKey(key);
        systemConfig.setValue(value);
        systemConfigDao.update(systemConfig);
    }


    @Override
    public void update(List<Map<String, String>> list) {
        for (Map<String, String> map : list) {
            SystemConfig systemConfig = new SystemConfig();
            systemConfig.setKey(map.get("name"));
            systemConfig.setValue(map.get("value"));
            systemConfigDao.update(systemConfig);

            if (systemConfig.getKey().equals("upload_type")) {
                updateUploadConfig(new Integer(systemConfig.getValue()));
            }
        }
    }


    @Transactional
    @Override
    public void updateUploadConfig(Integer id) {

        SystemConfig systemConfig = getById(id);
        List<SystemConfig> list = getByPid(systemConfig.getPid());

        list.stream()
                .filter(systemConfig2 -> !systemConfig2.getKey().equals("upload_type"))
                .collect(Collectors.toList()).forEach(systemConfig2 -> {
            systemConfig2.setValue("0");
            update(systemConfig2);
        });


        systemConfig.setValue("1");
        update(systemConfig);

        uploadConfig = null;
    }

    @Override
    public Map<String, Object> getUploadConfig() {
        if (uploadConfig != null) {
            return uploadConfig;
        }

        uploadConfig = new HashMap<>();
        SystemConfig systemConfig = getByKey("upload_type");
        List<SystemConfig> list = getByPid(new Integer(systemConfig.getValue()));
        uploadConfig.put(systemConfig.getKey(), systemConfig.getValue());
        list.forEach(systemConfig2 -> {
            uploadConfig.put(systemConfig2.getKey(), systemConfig2.getValue());
        });
        return uploadConfig;
    }


    @Override
    public Integer getAge() {

        return this.age;
    }


    @Override
    public void setAge(Integer age) {
        this.age = age;
    }

}
