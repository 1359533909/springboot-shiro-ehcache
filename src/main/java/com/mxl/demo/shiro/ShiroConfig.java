package com.mxl.demo.shiro;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mxl.demo.service.UserService;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig{
	/**
	 *创建shirofilterfactorybean
	 *
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
		filterFactoryBean.setSecurityManager(securityManager);
		/**
		 * 常用的内置过滤器
		 * 	anon:无需认证(登录)可以访问
		 * 	authc:必须认证才可以访问
		 * 	user:如果使用rememberme功能可以访问
		 * 	perms:该资源必须得到资源权限才可以访问
		 * 	role:该资源必须得到角色权限才可以访问
		 */
		Map<String, String> filterMap =new LinkedHashMap<>();
		filterMap.put("/**","anon");
		filterMap.put("/user/*","authc");
		filterMap.put("/user/*","user");
		filterMap.put("/add", "perms[user:add]");
		filterMap.put("/update", "perms[user:update]");
		filterFactoryBean.setLoginUrl("/toLogin");
		filterFactoryBean.setFilterChainDefinitionMap(filterMap);
		//未授权页面的跳转
		filterFactoryBean.setUnauthorizedUrl("/Unauthor");
		return filterFactoryBean;
		
	}
	/**
	 * 创建defaultwebsecuritymanager
	 *
	 */
	@Bean(name="defaultWebSecurityManager")
	public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(userRealm);
		return securityManager;
		
	}
	/**
	 * 创建realm
	 *
	 */
	@Bean(name="userRealm")
	public UserRealm userRealm() {
		UserRealm userRealm=new UserRealm();
		userRealm.setCredentialsMatcher(this.retryLimitCredentialsMatcher());
		return userRealm;
	}
	@Bean
	public ShiroDialect getShiroDialect() {
		return new ShiroDialect();
	}
	
	/**
	 * remenberMe记住我
	 */
	/**
	 *Cookie
	 */
	@Bean
	public SimpleCookie rememberMeCookie() {
	    //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
	    SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
	    //如果httyOnly设置为true，则客户端不会暴露给客户端脚本代码，使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击；
	    simpleCookie.setHttpOnly(true);
	    //记住我cookie生效时间,单位是秒
	    simpleCookie.setMaxAge(600);
	    return simpleCookie;
	}
	@Bean
	public CookieRememberMeManager rememberMeManager() {
	    CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
	    //rememberme cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度（128 256 512 位），通过以下代码可以获取
	    //KeyGenerator keygen = KeyGenerator.getInstance("AES");
	    //SecretKey deskey = keygen.generateKey();
	    //System.out.println(Base64.encodeToString(deskey.getEncoded()));
	    byte[] cipherKey = Base64.decode("wGiHplamyXlVB11UXWol8g==");
	    cookieRememberMeManager.setCipherKey(cipherKey);
	    cookieRememberMeManager.setCookie(rememberMeCookie());
	    return cookieRememberMeManager;
	}
	@Bean
	public SecurityManager securityManager() {
	    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
	    securityManager.setRealm(this.userRealm());
	    securityManager.setRememberMeManager(rememberMeManager());
	    return securityManager;
	}
	
	//设置密码重试次数
	 @Bean
	    public EhCacheManager ehCacheManager() {
	        EhCacheManager ehCacheManager = new EhCacheManager();
	        ehCacheManager.setCacheManagerConfigFile("classpath:ecache.xml");
	        return ehCacheManager;
	 }
	//设置密码重试次数
	 @Bean
	    public CredentialsMatcher retryLimitCredentialsMatcher() {
	        RetryLimitCredentialsMatcher retryLimitCredentialsMatcher = new RetryLimitCredentialsMatcher(this.ehCacheManager());
	        retryLimitCredentialsMatcher.setHashAlgorithmName("MD5");
//	        retryLimitCredentialsMatcher.setHashIterations(MD5Util.HASH_ITERATIONS);
	        retryLimitCredentialsMatcher.setStoredCredentialsHexEncoded(true);
	        return retryLimitCredentialsMatcher;
	    }
}
