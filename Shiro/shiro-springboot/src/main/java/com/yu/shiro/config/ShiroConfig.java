package com.yu.shiro.config;

import com.yu.shiro.realm.MyRealm;
import com.yu.shiro.realm.SecondRealm;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * shiro配置类
 *
 * @author elonlo
 * @date 2023/6/22 18:15
 */
@Configuration
public class ShiroConfig {

    private final MyRealm myRealm;

    private final SecondRealm secondRealm;

    @Autowired
    public ShiroConfig(MyRealm myRealm, SecondRealm secondRealm) {
        this.myRealm = myRealm;
        this.secondRealm = secondRealm;
    }

    /**
     * 配置defaultWebSecurityManager
     *
     * @return {@link DefaultWebSecurityManager}
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        // 1.创建defaultWebSecurityManager
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        // 2.创建加密对象,设置相关属性
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1024);

        // 3.将加密对象存储到myRealm中
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        secondRealm.setCredentialsMatcher(hashedCredentialsMatcher);

        // 4.修改默认认证策略
        defaultWebSecurityManager.setAuthenticator(modularRealmAuthenticator());

        // 5.设置多Realm
        Set<Realm> realms = new HashSet<>(16);
        realms.add(myRealm);
        realms.add(secondRealm);
        defaultWebSecurityManager.setRealms(realms);

        // 6.设置rememberMe
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());

        // 7.配置ehcache
        defaultWebSecurityManager.setCacheManager(ehCacheManager());

        return defaultWebSecurityManager;
    }

    /**
     * 配置ehcache
     *
     * @return {@link EhCacheManager}
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        // 创建ehCacheManager
        EhCacheManager ehCacheManager = new EhCacheManager();

        // 读取配置文件
        InputStream is = null;
        try {
            is = ResourceUtils.getInputStreamForPath("classpath:ehcache/ehcache-shiro.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        CacheManager cacheManager = new CacheManager(is);
        ehCacheManager.setCacheManager(cacheManager);
        return ehCacheManager;
    }

    /**
     * 配置认证策略
     *
     * @return {@link Authenticator}
     */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator() {
        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        // 修改默认认证策略
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

    /**
     * cookie属性设置
     *
     * @return {@link SimpleCookie}
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        // 需要与前端记住我的name属性值一致
        SimpleCookie cookie = new SimpleCookie("rememberMe");

        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(86400);
        return cookie;
    }

    /**
     * Shiro的cookie管理对象
     *
     * @return {@link CookieRememberMeManager}
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // 手动设置对称加密秘钥，防止重启系统后系统生成新的随机秘钥，防止导致客户端cookie无效
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }


    /**
     * 配置shiro内置过滤器拦截范围
     *
     * @return {@link DefaultShiroFilterChainDefinition}
     */
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition shiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();

        // 配置匿名访问路径
        shiroFilterChainDefinition.addPathDefinition("/user/userLogin", "anon");
        shiroFilterChainDefinition.addPathDefinition("/user/login", "anon");

        // 配置登出拦截器
        shiroFilterChainDefinition.addPathDefinition("/logout", "logout");

        // 配置认证访问路径
        shiroFilterChainDefinition.addPathDefinition("/**", "authc");
        // 添加存在用户的过滤器,在remember me功能中,用户拦截器用于验证并恢复用户的身份,启用用户拦截器才能使用remember me功能
        shiroFilterChainDefinition.addPathDefinition("/**", "user");

        return shiroFilterChainDefinition;
    }
}
