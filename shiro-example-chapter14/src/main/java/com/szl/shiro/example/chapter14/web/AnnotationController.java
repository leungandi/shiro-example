package com.szl.shiro.example.chapter14.web;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/annotation")
public class AnnotationController {
	
	private static Logger log = LoggerFactory.getLogger(AnnotationController.class);
	
	//表示当前Subject已经通过login进行了身份验证；即Subject. isAuthenticated()返回true。
	//当验证失败，其会抛出UnauthorizedException异常，此时可以使用Spring的ExceptionHandler（DefaultExceptionHandler）来进行拦截处理：
	@RequiresAuthentication
	@RequestMapping(value="",method=RequestMethod.GET)
	public String annotation() {
		log.info("=====annotation测试");
		return "loginSucess";
	}


}
