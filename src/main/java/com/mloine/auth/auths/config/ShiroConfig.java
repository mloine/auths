
package com.mloine.auth.auths.config;


import com.mloine.auth.auths.secutity.CorsUserAuthenticationFilter;
import com.mloine.auth.auths.secutity.PaitAuthorizingRealm;
import com.mloine.auth.auths.secutity.PaitCredentialsMatcher;
import com.mloine.auth.auths.secutity.PasswordHelper;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@ConditionalOnProperty(name = "shiro.enabled", matchIfMissing = true)
public class ShiroConfig {
    private static Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     * 加载shiroFilter权限控制规则
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean){
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("/auth/login", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/test/**", "anon");


        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/auth/logout", "logout");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }


    /**
     * shiro过滤器配置
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        logger.info("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        loadShiroFilterChain(shiroFilterFactoryBean);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/auth/login");
        // 登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl("/auth/loginSuccess");
        // 未授权界面，鉴权失败时返回信息给前端;
        shiroFilterFactoryBean.setUnauthorizedUrl("/auth/returnUnauthorizedMsg");

        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new CorsUserAuthenticationFilter ());

        shiroFilterFactoryBean.setFilters(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 权限  凭证 获取实现
     * @param matcher
     * @param cacheManager
     * @return
     */
    @Bean
    public PaitAuthorizingRealm paitAuthorizingRealm(PaitCredentialsMatcher matcher, ShiroRedisCacheManager cacheManager){
        PaitAuthorizingRealm realm = new PaitAuthorizingRealm();
        realm.setAuthenticationCacheName("shiro:authentication:jkg:");//缓存实现
        realm.setAuthorizationCacheName("shiro:authorization:jkg:");//缓存实现
        realm.setCredentialsMatcher(matcher);
        realm.setCacheManager(cacheManager);

        return realm;
    }

    @Bean(name = "shiroRedisCacheManager")
    public ShiroRedisCacheManager redisCacheManager(RedisTemplate<byte[], byte[]> shiroRedisTemplate) {
        return new ShiroRedisCacheManager(shiroRedisTemplate);
    }

    /**
     * 注入redis操作模板
     * @param factory
     * @return
     */
    @Bean(name = "shiroRedisTemplate")
    public RedisTemplate<byte[], byte[]> shiroRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        return template;
    }

    /**
     * 安全管理者
     * @param paitAuthorizingRealm
     * @return
     */
    @Bean
    public SecurityManager securityManager(PaitAuthorizingRealm paitAuthorizingRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(paitAuthorizingRealm);
        return securityManager;
    }

    /**
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(PasswordHelper.ALGORITHM_NAME);
        hashedCredentialsMatcher.setHashIterations(PasswordHelper.ITERATION_TIMES);
        return hashedCredentialsMatcher;
    }

    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator ();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }



}
