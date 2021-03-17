package com.wa.demo.shiro;

import com.wa.demo.shiro.models.Permission;
import com.wa.demo.shiro.models.Role;
import com.wa.demo.shiro.models.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CustomRealm
 * 2021-03-08 16:39
 *
 * @author wuao
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    // 使用JwtToken代替原生Token
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof JwtToken;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String userName = JwtUtils.getUsername(token);

        User user = userService.getUserByName(userName);
        if (user == null) {
            throw new AuthenticationException("user not exist.");
        }
        if (!JwtUtils.verify(token, userName, user.getPassword())) {
            throw new AuthenticationException("token invalid or expire.");
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = JwtUtils.getUsername((String) principalCollection.getPrimaryPrincipal());

        User user = userService.getUserByName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            simpleAuthorizationInfo.addRole(role.getName());
            for (Permission permission : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permission.getName());
            }
        }
        return simpleAuthorizationInfo;
    }
}
