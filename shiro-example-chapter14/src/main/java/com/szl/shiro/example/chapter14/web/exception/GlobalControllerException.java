package com.szl.shiro.example.chapter14.web.exception;

import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerException {
	
	private Logger log = LoggerFactory.getLogger(GlobalControllerException.class);
	
	
	/**
	 * 没有登录会抛出UnauthenticatedException异常,在此捕获，跳到登录页面
	 * 
	 * @return
	 */
	@ExceptionHandler(value= {UnauthenticatedException.class})
	public String unauthenticatedException() {
		log.info("=====没有登录,跳转......");
		return "login";
	}

}
