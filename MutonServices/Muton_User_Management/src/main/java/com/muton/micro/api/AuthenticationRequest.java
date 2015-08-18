package com.muton.micro.api;

import javax.ws.rs.BeanParam;

/**
 * Created by rohanw on 8/8/2015.
 */
public class AuthenticationRequest {

    @BeanParam
    private String username;
    @BeanParam
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
