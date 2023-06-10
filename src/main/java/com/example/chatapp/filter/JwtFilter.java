package com.example.chatapp.filter;

import com.example.chatapp.model.vo.JwtToken;
import com.example.chatapp.utilize.JwtUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JwtFilter extends BasicHttpAuthenticationFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        // 从请求头中获取 JWT Token
        String token = extractTokenFromRequest(httpServletRequest);

        if (isLoginRequest(httpServletRequest) || isSwaggerRequest(httpServletRequest)) {
            return true;
        }

        // 判断 Token 是否存在并且有效
        if (token != null && JwtUtils.validateToken(token)) {
            // 将 Token 交给 Shiro 进行登录验证
            getSubject(request, response).login(new JwtToken(token));
            return true;
        }

        return false;
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.toLowerCase().startsWith(BEARER_PREFIX.toLowerCase())) {
            return authorizationHeader.replaceFirst("(?i)" + BEARER_PREFIX, "").trim();
        }
        return null;
    }

    private boolean isLoginRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        // 根据实际情况判断登录请求的 URI，例如 "/login" 路径
        return uri.startsWith("/login")||uri.startsWith("/test");
    }

    private boolean isSwaggerRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        // 根据实际情况判断Swagger请求的 URI，例如以 "/swagger" 开头的路径
        return uri.startsWith("/swagger")||uri.startsWith("/v3/api-docs");
    }
}
