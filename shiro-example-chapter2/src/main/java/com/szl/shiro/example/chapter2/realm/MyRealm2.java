package com.szl.shiro.example.chapter2.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * 自定义Realm实现
 * @author SongZhangLiang
 * @date 2017年8月23日 下午4:25:37
 */
public class MyRealm2 implements Realm {
	
	//返回一个唯一的Realm名字 
	@Override
	public String getName() {
		return "MyRealm2";
	}
	
	//判断此Realm是否支持此Token  
	@Override
	public boolean supports(AuthenticationToken token) {
		//仅支持UsernamePasswordToken类型的Token  
		return token instanceof UsernamePasswordToken;
	}
	//根据Token获取认证信息  
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		String pwd = new String((char[])token.getCredentials());
		if(!"song".equals(userName)) {
			throw new UnknownAccountException("错误的用户名");
		}
		if(!"admin".equals(pwd)) {
			throw new IncorrectCredentialsException("错误的密码");
		}
		//如果身份认证验证成功，返回一个AuthenticationInfo实现；  
		return new SimpleAuthenticationInfo(userName+"@qq.com", pwd, getName());
	}

}
