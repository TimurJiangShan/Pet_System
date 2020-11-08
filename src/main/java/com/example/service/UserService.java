package com.example.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.example.dto.PageDataBody;
import com.example.dto.UserExecution;
import com.example.entity.User;
import com.example.entity.Top100;


public interface UserService {


	User findById(Integer userId);
	

	User findByName(String userName);
	

	User findByEmail(String email);
	

	User findByUserNameAndPassword(String userName,String password);
	

	User findByEmailAndPassword(String email, String password);
	

	List<Top100> scores(Integer limit);
	

	void updateScore(Integer score,Integer userId);
	

	void updateAvatar(String avatarBase64,String path,User user,HttpServletRequest request);
	

	PageDataBody<User> page(Integer pageNumber, Integer pageSize);
	

	UserExecution updateUser(User user);
	

	void deleteUserById(Integer userId);

	void deleteUserByName(String userName);
	

	UserExecution save(User user);
	
	UserExecution createUser(String username,String password,String email, String userType);
	

	int countUserAll();
	

	int countScore(Integer userId);

	int countToday();
	
	PageDataBody<User> pageForAdmin(String username, String email, Integer pageNumber, Integer pageSize);
	
	int countAllForAdmin(String username,String email);
	

	void updateAdmin(User user);

	void deleteAdmin(Integer id);

	List<String> findAllEmail();
}
