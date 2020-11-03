package com.example.dao;

import java.io.Serializable;
import java.util.Collection;
import org.apache.ibatis.annotations.Param;

/**
 * @author Jiangshan Zhao
 * @date 10/10/2020
 */
public interface CustomerDao {

	int insertBatch(@Param("customers") Collection<? extends Serializable> customers);
}
