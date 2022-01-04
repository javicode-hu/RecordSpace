package com.hjw.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author hjw
 * @date 2021年10月15日 16:40
 * @description
 */

public class JwtToken implements AuthenticationToken {

    private String token;


    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
