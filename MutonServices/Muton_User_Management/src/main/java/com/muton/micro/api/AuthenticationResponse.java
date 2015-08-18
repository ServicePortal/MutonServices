package com.muton.micro.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by rohanw on 8/8/2015.
 */
public class AuthenticationResponse {
    @JsonProperty
    private Date lastSuccessfulLogin;
    @JsonProperty
    private Date lastLoginAttempt;
    @JsonProperty
    private int failedAttempts;
    @JsonProperty
    private int userStatus;
    @JsonProperty
    private int status;
    @JsonProperty
    private String statusDescription;

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

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}
