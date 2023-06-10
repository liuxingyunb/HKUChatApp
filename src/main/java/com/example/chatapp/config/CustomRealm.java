package com.example.chatapp.config;

import com.example.chatapp.model.po.User;
import com.example.chatapp.model.vo.JwtToken;
import com.example.chatapp.service.UserService;
import com.example.chatapp.utilize.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
       /* UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());

        User user = userService.getUserByUsername(username);
        if (user == null) {
            return null;
        }

        return new SimpleAuthenticationInfo(username, user.getPassword(), getName());*/
        JwtToken jwtToken = (JwtToken) token;
        String tokenValue = (String) jwtToken.getPrincipal();

        if (JwtUtils.validateToken(tokenValue)) {
            // 从 JWT Token 中解析出用户名
            String username = JwtUtils.getUsernameFromToken(tokenValue);

            // 根据用户名获取用户信息
            User user = userService.getUserByUsername(username);
            if (user != null) {
                // 返回正确的认证信息
                return new SimpleAuthenticationInfo(username, tokenValue, getName());
            }
        }

        throw new AuthenticationException("Invalid token");

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken||token instanceof UsernamePasswordToken;
    }
}
