package com.szl.shiro.example.chapter10.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class MyListener implements SessionListener{

	@Override
	public void onStart(Session session) {
		System.out.println("创建会话(MyListener):"+session.getId());
		
	}

	@Override
	public void onStop(Session session) {
		System.out.println("会话注销(MyListener):"+session.getId());
		
	}

	@Override
	public void onExpiration(Session session) {
		System.out.println("会话过期(MyListener):"+session.getId());
		
	}

}
