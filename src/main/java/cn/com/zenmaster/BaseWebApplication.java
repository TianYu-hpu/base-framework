package cn.com.zenmaster;

import cn.com.zenmaster.config.JwtFilter;
import cn.com.zenmaster.shiro.*;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import redis.clients.jedis.JedisPool;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author tianyu
 * @date 2019-05-14
 * @description base web application
 */
@Slf4j
@RefreshScope
@Configuration
@EnableDiscoveryClient
@CrossOrigin(origins = "*")
@MapperScan(basePackages = "cn.com.zenmaster.mapper")
@EnableTransactionManagement
@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
public class BaseWebApplication {

    @Autowired
    private HikariDataSource dataSource;
    @Autowired
    private CustomSessionDao customSessionDao;


    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${server.servlet.session.cookie.name}")
    private String cookieName;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maxPoolSize;

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    public static void main(String[] args) {
        SpringApplication.run(BaseWebApplication.class, args);
    }

    @Bean
    public HikariDataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(jdbcUrl);
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);
        hikariDataSource.setMaximumPoolSize(maxPoolSize);
        log.info("url:{}", jdbcUrl);
        return hikariDataSource;
    }

    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        try {
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /*@Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        log.info("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //自定义拦截器
        Map<String, Filter> filterMap = new HashMap<>(16);
        filterMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断，因为前端模板采用了thymeleaf，这里不能直接使用 ("/static/**", "anon")来配置匿名访问，必须配置到每个静态目录
        *//*filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/html/**", "anon");*//*
        filterChainDefinitionMap.put("/login", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        //filterChainDefinitionMap.put("/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        filterChainDefinitionMap.put("/**", "jwt");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");

        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }*/

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * ）
     *
     * @return
     */
    @Bean
    public CustomHashedCredentialsMatcher hashedCredentialsMatcher() {
        CustomHashedCredentialsMatcher hashedCredentialsMatcher = new CustomHashedCredentialsMatcher(jedisCacheManager());
        //散列算法:这里使用sha算法;
        hashedCredentialsMatcher.setHashAlgorithmName("SHA-512");
        //散列的次数，比如散列两次，相当于 sha(sha(""));
        hashedCredentialsMatcher.setHashIterations(256);
        return hashedCredentialsMatcher;
    }

    @Bean
    public CustomShiroRealm curtomeShiroRealm() {
        CustomShiroRealm myShiroRealm = new CustomShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(curtomeShiroRealm());
        //关闭shiro自带的session,不用session了
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        securityManager.setSessionManager(customSessionManager());
        JedisCacheManager cacheManager = new JedisCacheManager();
        cacheManager.setCacheKeyPrefix("base_framework_");
        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }

    @Bean
    public JedisCacheManager jedisCacheManager() {
        JedisCacheManager cacheManager = new JedisCacheManager();
        return cacheManager;
    }

    @Bean
    public CustomSessionManager customSessionManager() {
        CustomSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setSessionDAO(customSessionDao);
        sessionManager.setSessionIdCookie(simpleCookie());
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setCacheManager(jedisCacheManager());
        return sessionManager;
    }

    @Bean
    public JedisPool jedisPool() {
        return new JedisPool(redisHost, redisPort);
    }

    @Bean
    public SimpleCookie simpleCookie() {
       SimpleCookie simpleCookie = new SimpleCookie();
       simpleCookie.setName(cookieName);
       return simpleCookie;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    /*@Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }*/

    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        //数据库异常处理
        mappings.setProperty("DatabaseException", "databaseError");
        mappings.setProperty("UnauthorizedException", "/user/403");
        // None by default
        exceptionResolver.setExceptionMappings(mappings);
        // No default
        exceptionResolver.setDefaultErrorView("error");
        // Default is "exception"
        exceptionResolver.setExceptionAttribute("exception");
        // No default
        //r.setWarnLogCategory("example.MvcLogger");
        return exceptionResolver;
    }

}
