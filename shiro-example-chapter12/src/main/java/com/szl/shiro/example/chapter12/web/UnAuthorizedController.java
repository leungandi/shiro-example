package com.szl.shiro.example.chapter12.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/unauthorized")
public class UnAuthorizedController {
	
	private static Logger log = LoggerFactory.getLogger(AuthenticatedController.class);
	
	@RequestMapping("")
	public String unAuthorized() {
		log.info("======没有权限/角色");
		return "unauthorized";
	}

}
