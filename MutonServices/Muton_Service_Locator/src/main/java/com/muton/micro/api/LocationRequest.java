package com.muton.micro.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by rohanw on 8/3/2015.
 */
public class LocationRequest {
    private String serviceId;
    private String version;
    private String serviceCode;

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
}
