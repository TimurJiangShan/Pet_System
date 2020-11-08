package com.example.service;

import com.example.dto.PageDataBody;
import com.example.entity.Collect;
import com.example.entity.Topic;

public interface CollectService {


	PageDataBody<Topic> page(Integer pageNumber, Integer pageSize,Integer uid);
	
    int insert(Collect collect);

    int delete(Integer uid,Integer tid);

    void deleteByUid(Integer uid);

    int count(Integer uid);

    int isCollect(Integer uid,Integer tid);

    int countByTid(Integer tid);
}
