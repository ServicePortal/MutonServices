package com.muton.micro.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by rohanw on 8/3/2015.
 */
public class LocationResponse {
    private String serviceId;
    private String version;
    private String serviceCode;
    private String url;
    private int status;
    private String errorMessage;

    @JsonProperty
    public String getServiceId() {
        return serviceId;
    }
    @JsonProperty
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
    @JsonProperty
    public String getVersion() {
        return version;
    }
    @JsonProperty
    public void setVersion(String version) {
        this.version = version;
    }
    @JsonProperty
    public String getServiceCode() {
        return serviceCode;
    }
    @JsonProperty
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
    @JsonProperty
    public String getUrl() {
        return url;
    }
    @JsonProperty
    public void setUrl(String url) {
        this.url = url;
    }
    @JsonProperty
    public int getStatus() {
        return status;
    }
    @JsonProperty
    public void setStatus(int status) {
        this.status = status;
    }
    @JsonProperty
    public String getErrorMessage() {
        return errorMessage;
    }
    @JsonProperty
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
