package com.mxl.demo.shiro;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;


/**
 *关闭程序之后,销毁缓存中记录的重试登录次数
 *
 */
@Component
public class SpringEhcacheShutdownListener implements ApplicationListener<ApplicationEvent>{

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		if (event instanceof ContextClosedEvent) {
            ApplicationContext context = ((ContextClosedEvent) event).getApplicationContext();
            EhCacheManager ehCacheManager = (EhCacheManager) context.getBean("ehCacheManager");
            if (ehCacheManager != null) {
                ehCacheManager.destroy();
            }
		}
	}

}
