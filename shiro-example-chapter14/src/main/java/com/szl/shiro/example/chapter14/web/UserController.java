package com.szl.shiro.example.chapter14.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class UserController {
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		log.info("=====登录首页");
		return "login";
	}
	
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String loginExe(HttpServletRequest req) {
		log.info("=====登录操作");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String error = null;
		if(!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
			error = "用户名/密码不能为空";
			req.setAttribute("error", error);
            return "login";
		}
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		try {
			
			subject.login(token);
			
		}catch (UnknownAccountException e) {
			error = "用户名错误";
			req.setAttribute("error", error);
		}catch (IncorrectCredentialsException e) {
			error = "密码错误";
			req.setAttribute("error", error);
		}catch (AuthenticationException e) {
			error = "错误："+e.getMessage();
			req.setAttribute("error", error);
			
		}
		if(StringUtils.hasText(error)) {
			 return "login";
		}else {
			 return "loginSucess";
		}
	}

}
