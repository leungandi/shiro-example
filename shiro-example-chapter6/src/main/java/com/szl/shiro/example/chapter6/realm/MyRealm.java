package com.szl.shiro.example.chapter6.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.szl.shiro.example.chapter6.entity.SysUsers;
import com.szl.shiro.example.chapter6.service.SysUsersService;
import com.szl.shiro.example.chapter6.service.impl.SysUsersServiceImpl;

public class MyRealm extends AuthorizingRealm {

	private SysUsersService sysUsersService = new SysUsersServiceImpl();

	/**
	 * 授权
	 * @author SongZhangLiang
	 * @date 2017年8月24日 下午10:18:34
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(sysUsersService.queryRoles(username));
		authorizationInfo.setStringPermissions(sysUsersService.queryPermissions(username));
		return authorizationInfo;
	}

	
	/**
	 * 认证
	 * @author SongZhangLiang
	 * @date 2017年8月24日 下午10:18:27
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		SysUsers user = sysUsersService.queryUser(username);
		if (null == user) {
			throw new UnknownAccountException();
		}

		if (!Boolean.TRUE.equals(user.getLocked())) {
			throw new LockedAccountException();
		}

		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
				user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
		
		return authenticationInfo;
	}

}
