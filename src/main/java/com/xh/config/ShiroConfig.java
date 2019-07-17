package com.xh.config;

import com.xh.filter.CheckPermissionShiroFilter;
import com.xh.filter.CustomShiroFilter;
import com.xh.filter.LoginShiroFilter;
import com.xh.service.IShiroService;
import com.xh.shiro.MyRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.Filter;

/**
 * @author xiaohe
 * @version V1.0.0
 */
@Slf4j
@Configuration
public class ShiroConfig {

    private IShiroService shiroServiceImpl;

    public ShiroConfig(IShiroService shiroServiceImpl) {
        this.shiroServiceImpl = shiroServiceImpl;
    }

    @Bean
    public SecurityManager securityManager(MyRealm realm, SessionManager sessionManager, CacheManager cacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setRememberMeManager(rememberMeManager());
        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(cacheManager);
        log.info("securityManager -----------> init");
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setLoginUrl("/login.html");
        filterFactoryBean.setUnauthorizedUrl("/unAuth.html");

        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        filtersMap.put("customShiroFilter", new CustomShiroFilter());
        filtersMap.put("checkPermission", new CheckPermissionShiroFilter(shiroServiceImpl));
        filtersMap.put("login", new LoginShiroFilter());
        filterFactoryBean.setFilters(filtersMap);

        Map<String, String> hashMap = new LinkedHashMap<>();
        // 当请求的 url 匹配到 LinkedHashMap 中的第一条记录后，不在匹配后面的记录
        // anon - org.apache.shiro.web.filter.authc.AnonymousFilter
        hashMap.put("/login", "anon");
        hashMap.put("/js/**", "anon");
        hashMap.put("/css/**", "anon");

        /* 开启注解后，这些受限资源都可以用注解形式替代 */
        hashMap.put("/7", "customShiroFilter");

        // 符合第一条  url 表达式 就不会执行第二条 url 表达式
//        hashMap.put("/company/list", "customShiroFilter")
//        hashMap.put("/company/**", "checkPermission")
        // authc - org.apache.shiro.web.filter.authc.FormAuthenticationFilter
        // 同一资源，多条过滤器链  顺序执行
        hashMap.put("/company/**", "login,checkPermission");

        // 需要登录权限
        hashMap.put("/2", "customShiroFilter,authc");
        //需要admin:test3权限
        hashMap.put("/3", "perms[admin:test3]");
        //需要admin:test3权限
        hashMap.put("/4", "roles[admin]");

        // 定义多个 过滤器
        hashMap.put("/9", "customShiroFilter,checkPermission");
        // hashMap.put("/**", "checkPermission")

        filterFactoryBean.setFilterChainDefinitionMap(hashMap);
        return filterFactoryBean;
    }


    //=======================rememberMe========================

    /**
     * cookie对象;
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     *
     * @return cookie
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间7天 ,单位秒;-->
        simpleCookie.setMaxAge(7 * 24 * 60 * 60);
        simpleCookie.setHttpOnly(true);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，
     * 而且要将这个rememberMe管理器设置到securityManager中
     *
     * @return cookie manager
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());

        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
//        KeyGenerator keygen = null;
//        try {
//            keygen = KeyGenerator.getInstance("AES");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        SecretKey deskey = keygen.generateKey();
//        // CipherKey的key的生成
//        System.out.println(Base64.encodeToString(deskey.getEncoded()));

        cookieRememberMeManager.setCipherKey(Base64.decode("tiVV6g3uZBGfgshesAQbjA=="));
        return cookieRememberMeManager;
    }

    // shiro-redis
    //====== session共享 ========

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return redis
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
//        redisManager.setHost("47.98.142.170:6379")
//        redisManager.setDatabase(0)
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1024);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMaxWaitMillis(10000);
        // 初始化JedisPool
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.199.168", 6379, 8000);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.0.45", 6379, 8000);
        redisManager.setJedisPool(jedisPool);

        return redisManager;
    }

    @Bean
    RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    @Bean
    DefaultWebSessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        return sessionManager;
    }

    @Bean
    RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        // redis中针对不同用户缓存(此处的id需要对应user实体中的id字段,用于唯一标识)
        redisCacheManager.setPrincipalIdFieldName("userId");
        // 用户权限信息缓存时间
        redisCacheManager.setExpire(200000);
        return redisCacheManager;
    }


    // ===================== 注解代理 ===================

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }
}
