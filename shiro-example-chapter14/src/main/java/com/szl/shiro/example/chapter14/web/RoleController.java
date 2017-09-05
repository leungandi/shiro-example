package com.szl.shiro.example.chapter14.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/role")
public class RoleController {
	
	private static Logger log = LoggerFactory.getLogger(RoleController.class);

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String authenticated(HttpServletRequest req) {
		log.info("========判断角色");
		Subject subject = SecurityUtils.getSubject();
		if (subject.hasRole("admin")) {
			req.setAttribute("username", subject.getPrincipal());
			return "role";
		} else {
			return "unauthorized";
		}
	}

}
