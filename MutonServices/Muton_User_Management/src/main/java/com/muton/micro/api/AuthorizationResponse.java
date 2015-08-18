package com.muton.micro.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by rohanw on 8/8/2015.
 */
public class AuthorizationResponse {
    @JsonProperty
    private int status;
    @JsonProperty
    private String statusDescription;

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
