package com.mxl.demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.mxl.demo.entity.User;
import com.mxl.demo.service.UserService;

public class UserRealm extends AuthorizingRealm{
	@Autowired
	private  UserService userService;
	

	
	/**
	 *执行授权逻辑
	 *
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		System.out.println("执行授权逻辑--------------------");
//		给资源进行授权
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
//		添加授权内容
//		info.addStringPermission("user:add");
		Subject subject = SecurityUtils.getSubject();
		String principal = (String) subject.getPrincipal();
		User user = userService.findUserByName(principal);
		info.addStringPermission(user.getPerms());
		return info;
	}
	/**
	 * 执行认证逻辑
	 *
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("执行认证逻辑----------------------");
		/**
		String principal=(String) authcToken.getPrincipal();
		String credentials=(String) authcToken.getCredentials();
		//以上方法获取不到数据
		 */
		UsernamePasswordToken token=(UsernamePasswordToken) authcToken;
		String principal =token.getUsername();
		char[] credentials = token.getPassword();
		
		//数据库数据
		String username;
		String password;
		User user = userService.findUserByName(principal);
		if(user==null) {
			return null;
		}else {
		username=user.getUsername();
		password=user.getPassword();
		}
		/**
		 * 不在需要加密,在retryLimitCredentialsMatcher已经给我们加密了
		 */
//		Md5Hash str=new Md5Hash(credentials);
//		String string=str.toString();
//		token.setPassword(string.toCharArray()); 
		
		UsernamePasswordToken token2=(UsernamePasswordToken) authcToken;
		
		System.out.println("数据库密码:");
		for(char c:token2.getPassword()) {
		System.out.print(c);
		}
		System.out.println();
		
		
		if(!principal.equals(username)) {
			return null;//shiro底层抛出UnknownAccountException异常
		}
		return new SimpleAuthenticationInfo(username, password, getName());
	}

}
