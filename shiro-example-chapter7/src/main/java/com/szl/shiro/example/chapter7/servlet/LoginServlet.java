package com.szl.shiro.example.chapter7.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="loginServlet",urlPatterns="/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String error = null;
		if(!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
			error = "用户名/密码不能为空";
			req.setAttribute("error", error);
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
            return;
		}
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
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
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
		}else {
			req.setAttribute("username", username);
			req.getRequestDispatcher("/WEB-INF/jsp/loginSucess.jsp").forward(req, resp);
		}
		
		

	}

}
