package com.ant.be.shiro.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.ant.be.shiro.MyFormAuthenticationFilterextends;
import com.ant.be.shiro.RetryLimitHashedCredentialsMatcher;
import com.ant.be.shiro.session.SessionManager;

@Configuration
public class ShiroConfig {
	@Value("${spring.redis.host}")  
    private String host;  
    @Value("${spring.redis.port}")  
    private int port;  
    @Value("${spring.redis.timeout}")  
    private int timeout;  
    
    private RedisCacheManager cacheManager;
    
  
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {  
        System.out.println("ShiroConfiguration.shirFilter()");  
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();  
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,Filter> filter=shiroFilterFactoryBean.getFilters();
        filter.put("authc", new MyFormAuthenticationFilterextends());
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();  
        //注意过滤器配置顺序 不能颠倒  
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl  
        filterChainDefinitionMap.put("/logout", "logout");  
        // 配置不会被拦截的链接 顺序判断  
        filterChainDefinitionMap.put("/static/**", "anon"); 
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/login/getUserBySessionId", "authc");  
        filterChainDefinitionMap.put("/login/**", "anon");  
        filterChainDefinitionMap.put("/course/**", "anon"); 
        filterChainDefinitionMap.put("/distinguish/**", "anon"); 
        filterChainDefinitionMap.put("/course-details/**", "anon");
        filterChainDefinitionMap.put("/course_reserve/**", "anon");
        filterChainDefinitionMap.put("/not_verifying/**", "anon");
        filterChainDefinitionMap.put("/teacher/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");  
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据  
        shiroFilterFactoryBean.setLoginUrl("/login/unauth");  
        // 登录成功后要跳转的链接  
        shiroFilterFactoryBean.setSuccessUrl("/login/getUserBySessionId");  
        //未授权界面;  
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");  
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);  
        return shiroFilterFactoryBean;  
    }  
    
    
  
    /**  
     * 凭证匹配器  
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了  
     * ）  
     *  
     * @return  
     */  
    @Bean  
    public HashedCredentialsMatcher hashedCredentialsMatcher() {  
    	RetryLimitHashedCredentialsMatcher hashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher();  
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;  
        hashedCredentialsMatcher.setHashIterations(1024);//散列的次数，比如散列两次，相当于 md5(md5(""));  
        return hashedCredentialsMatcher;  
    }  
  
    /**
     * 多realm认证
     * @return
     */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        //自己重写的ModularRealmAuthenticator
    	ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }
    
    @Bean  
    public MyShiroRealm myShiroRealm() {  
        MyShiroRealm myShiroRealm = new MyShiroRealm();  
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());  
        return myShiroRealm;  
    }  
    
    @Bean  
    public ByCodeShiroRealm byCodeShiroRealm() {  
    	ByCodeShiroRealm byCodeShiroRealm = new ByCodeShiroRealm(); 
    	byCodeShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());  
        return byCodeShiroRealm;  
    }  
  
  
    @Bean  
    public SecurityManager securityManager(CookieRememberMeManager rememberMeManager) {  
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();  
//        securityManager.setRealm(myShiroRealm());
        securityManager.setAuthenticator(modularRealmAuthenticator());
        List<Realm> realms = new ArrayList<>();
        realms.add(myShiroRealm());
        realms.add(byCodeShiroRealm());
        securityManager.setRealms(realms);
        // 自定义session管理 使用redis  
        securityManager.setSessionManager(sessionManager());  
        // 自定义缓存实现 使用redis  
        securityManager.setRememberMeManager(rememberMeManager);
        securityManager.setCacheManager((CacheManager) cacheManager());  
        return securityManager;  
    }  
    
    /**
     * rememberMe管理器, cipherKey生成见{@code Base64Test.java}
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCipherKey(Base64.decode("Z3VucwAAAAAAAAAAAAAAAA=="));
        manager.setCookie(rememberMeCookie);
        return manager;
    }

    /**
     * 记住密码Cookie
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(7 * 24 * 60 * 60);//7天
        return simpleCookie;
    } 
  
    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setExpire(1800000);// 配置缓存过期时间
        redisManager.setTimeout(timeout);
        // redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }
  
    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    //自定义sessionManager  
//    @Bean  
//    public SessionManager sessionManager() {  
//        MySessionManager mySessionManager = new MySessionManager();  
//        mySessionManager.setSessionDAO(redisSessionDAO());  
//        return mySessionManager;  
//    }  
  
    //自定义sessionManager  
    @Bean
    public SessionManager sessionManager() {    
    	SessionManager se=new SessionManager();
//    	se.setCacheManager(cacheManager);// 换成Redis的缓存管理器  
//    	se.setSessionDAO(redisSessionDAO());  
    	se.setDeleteInvalidSessions(true);  
    	se.setGlobalSessionTimeout(15*24*60*60*1000);  
    	se.setSessionValidationSchedulerEnabled(true);  
        return se;  
    } 
  
    /** 
     * 开启shiro aop注解支持. 
     * 使用代理方式;所以需要开启代码支持; 
     * 
     * @param securityManager 
     * @return 
     */  
    @Bean  
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {  
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();  
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);  
        return authorizationAttributeSourceAdvisor;  
    }  
  
    /** 
     * 注册全局异常处理 
     * @return 
     */  
    @Bean(name = "exceptionHandler")  
    public HandlerExceptionResolver handlerExceptionResolver() {  
        return new MyExceptionHandler();  
    }  
}
