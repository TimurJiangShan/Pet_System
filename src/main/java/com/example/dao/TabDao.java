package com.example.dao;

import java.util.List;

import com.example.entity.Tab;

/**
 * Parent blokc interface
 * @author Jiangshan
 * 2020年10月1日
 * 下午8:57:48
 * TBD
 */
public interface TabDao {

	/**
	 * 查询所有板块
	 * @return
	 */
	List<Tab> selectAll();
}
