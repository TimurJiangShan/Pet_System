package com.example.handler;

import javax.servlet.http.HttpServletRequest;

import com.example.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.dto.Result;


@ControllerAdvice
@Component
public class GlobalExceptionHandler {
	
	private HttpStatus getStatus(HttpServletRequest request) {
	    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
	    if (statusCode == null) {
	      return HttpStatus.INTERNAL_SERVER_ERROR;
	    }
	    return HttpStatus.valueOf(statusCode);
	  }
	
	//error page
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
	    e.printStackTrace();
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", e.getMessage());
	    mav.addObject("errorCode", getStatus(request));
	    mav.setViewName("error/error");
	    return mav;
	  }
	
	//interface
	@ExceptionHandler(value = ApiException.class)
	@ResponseBody
	public Result<String> jsonErrorHandler(ApiException e) throws ApiException{
		return new Result<>(false, e.getMessage());
	}
}
