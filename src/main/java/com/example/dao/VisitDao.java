package com.example.dao;

import java.util.List;

import com.example.entity.User;
import com.example.entity.Visit;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author Jiangshan Zhao
 * TBD
 */
public interface VisitDao {

	/**
	 * 分页查询
	 * @param vid 被访问者ID
	 * @param start
	 * @param limit
	 * @return
	 */
	List<User> select(@Param("vid") Integer vid, @Param("start") Integer start, @Param("limit") Integer limit);
	
	/**
	 * 统计被访问的数量
	 * @param vid 被访问者ID
	 * @return
	 */
	int count(@Param("vid") Integer vid);
	
	/**
	 * 是否已存在访问记录
	 * @param uid
	 * @param vid
	 * @return
	 */
	int isVisit(@Param("uid") Integer uid,@Param("vid") Integer vid);
	
	/**
	 * 添加访问记录
	 * @param visit
	 * @return
	 */
	int insert(Visit visit);
	
	/**
	 * 删除访问记录
	 * @param uid 访问者ID
	 * @param vid 被访问者ID
	 * @return
	 */
	int delete(@Param("uid") Integer uid,@Param("vid") Integer vid);
	
	/**
	 * 更新访问记录
	 * @param visit
	 * @return
	 */
	int update(Visit visit);
}
