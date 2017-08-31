package com.szl.shiro.example.chapter12.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/permission")
public class PermissionController {
	
	private static Logger log = LoggerFactory.getLogger(PermissionController.class);

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String authenticated(HttpServletRequest req) {
		log.info("=============判断权限");
		Subject subject = SecurityUtils.getSubject();
		if (subject.isPermitted("user:create")) {
			req.setAttribute("username", subject.getPrincipal());
			return "hasPermission";
		} else {
			return "unauthorized";
		}
	}

}
