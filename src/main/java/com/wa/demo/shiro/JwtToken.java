package com.wa.demo.shiro;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * JwtToken
 * 2021-03-09 16:53
 *
 * @author wuao
 */
@Data
@AllArgsConstructor
public class JwtToken implements AuthenticationToken {

    private String token;

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
