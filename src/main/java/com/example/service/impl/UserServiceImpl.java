package com.example.service.impl;

import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.example.exception.OperationFailedException;
import com.example.exception.OperationRepeaException;
import com.example.exception.OperationSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.example.dao.UserDao;
import com.example.dto.PageDataBody;
import com.example.dto.UserExecution;
import com.example.entity.User;
import com.example.entity.Top100;
import com.example.enums.InsertUserEnum;
import com.example.enums.UpdateUserEnum;
import com.example.service.CollectService;
import com.example.service.NoticeService;
import com.example.service.ReplyService;
import com.example.service.TopicService;
import com.example.service.UserService;
import com.example.store.StorageService;
import com.example.util.CookieAndSessionUtil;
import com.example.util.StringUtil;
import com.example.util.bcrypt.BCryptPasswordEncoder;

@Service
public class UserServiceImpl implements UserService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserDao rootUserDao;
	

	@Autowired
	private StorageService storageService;
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private CollectService collectService;
	
	@Autowired
	private NoticeService noticeService;


	@Override
	public User findById(Integer userId) {	
		return rootUserDao.selectByUserId(userId);
	}

	@Override
	public User findByName(String userName) {
		return rootUserDao.selectByUserName(userName);
	}

	@Override
	public User findByEmail(String email) {
		return rootUserDao.selectByEmail(email);
	}

	@Override
	public User findByUserNameAndPassword(String userName, String password) {
		return rootUserDao.selectByUserNameAndPassword(userName, password);
	}


	@Override
	public User findByEmailAndPassword(String email, String password) {
		return rootUserDao.selectByEmailAndPassword(email, password);
	}


	@Override
	public List<Top100> scores(Integer limit) {
		return rootUserDao.selectByScore(limit);
	}

	@Override
	public PageDataBody<User> page(Integer pageNumber, Integer pageSize) {
		List<User> list = rootUserDao.selectAll((pageNumber - 1) * pageSize, pageSize);
		int totalRow = rootUserDao.countUserAll();
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	@Transactional
	@Override
	public UserExecution updateUser(User user) {
		try {
			if(user == null) {
				throw new OperationRepeaException("user not exist");
			}else {
				int updateUser = rootUserDao.updateUser(user);
				User rootUser = rootUserDao.selectByUserName(user.getUserName());
				if(updateUser <= 0) {
					throw new OperationFailedException("edit failure");
				}else {
					return new UserExecution(user.getUserName(),UpdateUserEnum.SUCCESS,rootUser);
				}
			}
		} catch (OperationRepeaException e1) {
			throw e1;
		} catch (OperationFailedException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new OperationSystemException("update RootUser error "+e.getMessage());
		}
	}


	@Override
	public void deleteUserById(Integer userId) {
		rootUserDao.deleteUserByUserId(userId);	
	}

	@Override
	public void deleteUserByName(String userName) {
		rootUserDao.deleteUserByUserName(userName);
	}


	@Transactional
	@Override
	public UserExecution save(User user) {
		try {
			if(user.getUserName() == null && user.getUserName().equals("")) {
				throw new OperationRepeaException("username not null");
			}
			if(user.getPassword() == null && user.getPassword().equals("")) {
				throw new OperationRepeaException("password not null");
			}
			User userName = rootUserDao.selectByUserName(user.getUserName());
			if(userName != null) {
				throw new OperationRepeaException("name exist");
			}else {
				int insertUser = rootUserDao.insertUser(user);
				User rootUser = rootUserDao.selectByUserName(user.getUserName());
				if(insertUser <= 0) {
					throw new OperationFailedException("register fail");
				}else {
					return new UserExecution(user.getUserName(),InsertUserEnum.SUCCESS,rootUser);
				}
			}
		} catch (OperationRepeaException e1) {
			throw e1;
		} catch (OperationFailedException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw new OperationSystemException("insert into RootUser error "+e.getMessage());
		}
	}

	public UserExecution createUser(String username,String password,String email, String userType) {
		User user = new User();
		user.setUserName(username);

		user.setPassword(new BCryptPasswordEncoder().encode(password));
		user.setScore(10);
		user.setEmail(email);
		user.setUrl(null);
		user.setThirdId(null);
		user.setReceiveMsg(false);
		user.setCreateDate(new Date());
		user.setUpdateDate(null);
		user.setIsBlock(false);
		user.setThirdAccessToken(StringUtil.getUUID());
		user.setStatusCd("1000");
		user.setUserType("2");
		user.setAvatar("/resources/images/default-avatar.jpg");
		user.setSignature("nothing here");
		user.setUserType(userType);
		return save(user);
	}

	@Override
	public int countUserAll() {
		return rootUserDao.countUserAll();
	}

	@Transactional
	@Override
	public void updateScore(Integer score, Integer userId) {
		rootUserDao.updateScore(score, userId);
	}

	@Override
	public int countScore(Integer userId) {
		return rootUserDao.countScore(userId);
	}

	@Override
	public int countToday() {
		return rootUserDao.countToday();
	}


	@Override
	@Transactional
	public void updateAvatar(String avatarBase64, String path, User user, HttpServletRequest request) {

		String avatarURL = storageService.store(avatarBase64, Paths.get(path));
		user.setAvatar(avatarURL);
		user.setUpdateDate(new Date());

		updateUser(user);

		topicService.updateTopicAvatar(user);

		CookieAndSessionUtil.removeSession(request, "user");
		CookieAndSessionUtil.setSession(request, "user", user);
	}

	@Override
	public PageDataBody<User> pageForAdmin(String username, String email, Integer pageNumber, Integer pageSize) {
		List<User> list = rootUserDao.selectAllForAdmin(username, email, (pageNumber - 1) * pageSize, pageSize);
		int totalRow = countAllForAdmin(username, email);
		return new PageDataBody<>(list, pageNumber, pageSize, totalRow);
	}

	@Override
	public int countAllForAdmin(String username, String email) {
		return rootUserDao.countAllForAdmin(username, email);
	}


	@Override
	public void updateAdmin(User user) {


		if(!StringUtils.isEmpty(user.getPassword())) {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		}
		user.setUpdateDate(new Date());
		updateUser(user);
	}


	@Override
	@Transactional
	public void deleteAdmin(Integer id) {
		User user = findById(id);

		topicService.deleteByAuthor(user.getUserName());

		replyService.deleteByReplyAuthorName(user.getUserName());

		collectService.deleteByUid(user.getUserId());

		noticeService.deleteByTargetAuthorName(user.getUserName());

		deleteUserById(user.getUserId());
	}

	@Override
	public List<String> findAllEmail() {
		return rootUserDao.findAllEmail();
	}

}
