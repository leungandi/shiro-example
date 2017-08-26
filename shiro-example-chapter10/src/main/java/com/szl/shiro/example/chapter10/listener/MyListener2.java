package com.szl.shiro.example.chapter10.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * Application Lifecycle Listener implementation class MyListener2
 *
 */

public class MyListener2 extends SessionListenerAdapter{

	@Override
	public void onStart(Session session) {
		System.out.println("创建会话(MyListener2):"+session.getId());
	}
       

}
