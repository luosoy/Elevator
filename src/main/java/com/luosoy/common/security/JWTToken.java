package com.luosoy.common.security;

import org.apache.shiro.authc.AuthenticationToken;


public class JWTToken implements AuthenticationToken {


    /**
     * 
     */
    private static final long serialVersionUID = -6732952295308890836L;
    
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    public Object getPrincipal() {
        return token;
    }

    public Object getCredentials() {
        return token;
    }
}
