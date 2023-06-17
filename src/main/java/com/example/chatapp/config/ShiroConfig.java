package com.example.chatapp.config;

import com.example.chatapp.config.CustomRealm;
import com.example.chatapp.filter.JwtFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/*@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        return new CustomRealm();
    }

    @Bean
    public SecurityManager securityManager(@Qualifier("realm") Realm realm) {
        // 创建 SecurityManager 并设置 Realm
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        // 创建 ShiroFilterFactoryBean 并设置 SecurityManager
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/login/**", "anon"); // 登录接口放行

        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilter;
    }
}*/

@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        return new CustomRealm();
    }

    @Bean
    public SecurityManager securityManager(@Qualifier("realm") Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        // Add the JWT filter
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("jwt", jwtFilter());
        shiroFilter.setFilters(filters);

        // Set the filter chain definition map
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap());

        return shiroFilter;
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    @Bean
    public Map<String, String> filterChainDefinitionMap() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/login/**", "anon"); // Allow access to login endpoints

        // 放行Swagger相关的URL路径
        filterChainDefinitionMap.put("/swagger-ui/index.html#", "anon");
        filterChainDefinitionMap.put("/swagger-ui/**", "anon");
        filterChainDefinitionMap.put("/v3/api-docs", "anon");
        filterChainDefinitionMap.put("/**/**", "anon");

        filterChainDefinitionMap.put("/**", "jwt"); // Apply JWT filter to other endpoints
        return filterChainDefinitionMap;
    }
}



