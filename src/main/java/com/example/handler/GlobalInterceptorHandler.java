package com.example.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.example.entity.User;
import com.example.web.front.BaseController;


public class GlobalInterceptorHandler implements HandlerInterceptor{
	
	@Autowired
	private BaseController baseController;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		User user = baseController.getUser(request);

		if(user == null) {
			request.setAttribute("message", "you need to login");
			request.getRequestDispatcher("/login").forward(request, response);
		}

		if(user!= null && user.getIsBlock()) {
			throw new RuntimeException("Your account has been locked and you cannot continue to operate. Please contact your system administrator to resolve. Return<a href='/' style=\"color: #4078c0;text-decoration: underline;\">Homepage</a>");
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
