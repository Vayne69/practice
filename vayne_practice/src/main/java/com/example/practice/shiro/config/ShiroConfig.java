// package com.example.practice.shiro.config;
//
// import com.example.practice.shiro.init.ShiroRealm;
// import com.example.practice.shiro.init.ShiroSessionIdGenerator;
// import com.example.practice.shiro.init.ShiroSessionManager;
// import com.example.practice.shiro.util.SHA256Util;
// import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
// import org.apache.shiro.mgt.SecurityManager;
// import org.apache.shiro.session.mgt.SessionManager;
// import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
// import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
// import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
// import org.crazycake.shiro.RedisCacheManager;
// import org.crazycake.shiro.RedisManager;
// import org.crazycake.shiro.RedisSessionDAO;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// import java.util.LinkedHashMap;
// import java.util.Map;
//
// /**
//  * @author : Yang Jian
//  * @date : 2019/9/2 17:28
//  */
// @Configuration
// public class ShiroConfig {
//     private final String CACHE_KEY = "shiro:cache:";
//     private final String SESSION_KEY = "shiro:session:";
//     private final int EXPIRE = 1800;
//     //Redis配置
//     @Value("${spring.redis.host}")
//     private String host;
//     @Value("${spring.redis.port}")
//     private int port;
//     @Value("${spring.redis.timeout}")
//     private int timeout;
//     @Value("${spring.redis.password}")
//     private String password;
//
//     /**
//      * 开启Shiro-aop注解支持
//      *
//      * @Attention 使用代理方式所以需要开启代码支持
//      * @Author Sans
//      * @CreateTime 2019/6/12 8:38
//      */
//     @Bean
//     public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//         AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//         authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//         return authorizationAttributeSourceAdvisor;
//     }
//
//     /**
//      * Shiro基础配置
//      *
//      * @Author Sans
//      * @CreateTime 2019/6/12 8:42
//      */
//     @Bean
//     public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
//         ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//         shiroFilterFactoryBean.setSecurityManager(securityManager);
//         Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//         filterChainDefinitionMap.put("/static/**", "anon");
//         filterChainDefinitionMap.put("/userLogin/**", "anon");
//         filterChainDefinitionMap.put("/**", "anon");
//         shiroFilterFactoryBean.setLoginUrl("/userLogin/unauth");
//         shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//         return shiroFilterFactoryBean;
//     }
//
//     @Bean
//     public SecurityManager securityManager() {
//         DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//         // 自定义Ssession管理
//         securityManager.setSessionManager(sessionManager());
//         // 自定义Cache实现
//         securityManager.setCacheManager(cacheManager());
//         // 自定义Realm验证
//         securityManager.setRealm(shiroRealm());
//         return securityManager;
//     }
//
//     /**
//      * 身份验证器
//      *
//      * @Author Sans
//      * @CreateTime 2019/6/12 10:37
//      */
//     @Bean
//     public ShiroRealm shiroRealm() {
//         ShiroRealm shiroRealm = new ShiroRealm();
//         shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//         return shiroRealm;
//     }
//
//     /**
//      * 凭证匹配器
//      * 将密码校验交给Shiro的SimpleAuthenticationInfo进行处理,在这里做匹配配置
//      *
//      * @Author Sans
//      * @CreateTime 2019/6/12 10:48
//      */
//     @Bean
//     public HashedCredentialsMatcher hashedCredentialsMatcher() {
//         HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//         // 散列算法:这里使用SHA256算法;
//         credentialsMatcher.setHashAlgorithmName(SHA256Util.HASH_ALGORITHM_NAME);
//         // 散列的次数，比如散列两次，相当于 md5(md5(""));
//         credentialsMatcher.setHashIterations(SHA256Util.HASH_ITERATIONS);
//         return credentialsMatcher;
//     }
//
//     /**
//      * 配置Redis管理器
//      *
//      * @Attention 使用的是shiro-redis开源插件
//      * @Author Sans
//      * @CreateTime 2019/6/12 11:06
//      */
//     @Bean
//     public RedisManager redisManager() {
//         RedisManager redisManager = new RedisManager();
//         redisManager.setHost(host);
//         redisManager.setPort(port);
//         redisManager.setTimeout(timeout);
//         redisManager.setPassword(password);
//         return redisManager;
//     }
//
//     /**
//      * 配置Cache管理器
//      * 用于往Redis存储权限和角色标识
//      *
//      * @Attention 使用的是shiro-redis开源插件
//      * @Author Sans
//      * @CreateTime 2019/6/12 12:37
//      */
//     @Bean
//     public RedisCacheManager cacheManager() {
//         RedisCacheManager redisCacheManager = new RedisCacheManager();
//         redisCacheManager.setRedisManager(redisManager());
//         redisCacheManager.setKeyPrefix(CACHE_KEY);
//         // 配置缓存的话要求放在session里面的实体类必须有个id标识
//         redisCacheManager.setPrincipalIdFieldName("userId");
//         return redisCacheManager;
//     }
//
//     /**
//      * SessionID生成器
//      *
//      * @Author Sans
//      * @CreateTime 2019/6/12 13:12
//      */
//     @Bean
//     public ShiroSessionIdGenerator sessionIdGenerator() {
//         return new ShiroSessionIdGenerator();
//     }
//
//     /**
//      * 配置RedisSessionDAO
//      *
//      * @Attention 使用的是shiro-redis开源插件
//      * @Author Sans
//      * @CreateTime 2019/6/12 13:44
//      */
//     @Bean
//     public RedisSessionDAO redisSessionDAO() {
//         RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
//         redisSessionDAO.setRedisManager(redisManager());
//         redisSessionDAO.setSessionIdGenerator(sessionIdGenerator());
//         redisSessionDAO.setKeyPrefix(SESSION_KEY);
//         redisSessionDAO.setExpire(EXPIRE);
//         return redisSessionDAO;
//     }
//
//     /**
//      * 配置Session管理器
//      *
//      * @Author Sans
//      * @CreateTime 2019/6/12 14:25
//      */
//     @Bean
//     public SessionManager sessionManager() {
//         ShiroSessionManager shiroSessionManager = new ShiroSessionManager();
//         shiroSessionManager.setSessionDAO(redisSessionDAO());
//         return shiroSessionManager;
//     }
// }
