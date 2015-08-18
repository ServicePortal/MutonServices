package com.muton.micro.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.ws.rs.BeanParam;
import java.util.Date;

/**
 * Created by rohanw on 8/8/2015.
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    @JsonProperty
    @BeanParam
    private long id;
    @JsonProperty
    @BeanParam
    private String fullName;
    @JsonProperty
    @BeanParam
    private String email;
    @JsonProperty
    private String username;
    @JsonProperty
    private String password;
    @JsonProperty
    private Date lastSuccessfulLogin;
    @JsonProperty
    private Date lastLoginAttempt;
    @JsonProperty
    private int failedAttempts;
    @JsonProperty
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public Date getLastSuccessfulLogin() {
        return lastSuccessfulLogin;
    }

    public void setLastSuccessfulLogin(Date lastSuccessfulLogin) {
        this.lastSuccessfulLogin = lastSuccessfulLogin;
    }

    public Date getLastLoginAttempt() {
        return lastLoginAttempt;
    }

    public void setLastLoginAttempt(Date lastLoginAttempt) {
        this.lastLoginAttempt = lastLoginAttempt;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
