package com.muton.micro.jdbi;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by rohanw on 8/8/2015.
 */
public class JDBCFactory {
    @NotEmpty
    private String driver;
    @NotEmpty
    private String url;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @Min(1)
    @Max(10)
    private int minConnections;
    @Min(10)
    @Max(100)
    private int maxConnections;
    @JsonProperty
    public String getDriver() {
        return driver;
    }
    @JsonProperty
    public void setDriver(String driver) {
        this.driver = driver;
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
    public String getUsername() {
        return username;
    }
    @JsonProperty
    public void setUsername(String username) {
        this.username = username;
    }
    @JsonProperty
    public String getPassword() {
        return password;
    }
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
    @JsonProperty
    public int getMinConnections() {
        return minConnections;
    }
    @JsonProperty
    public void setMinConnections(int minConnections) {
        this.minConnections = minConnections;
    }
    @JsonProperty
    public int getMaxConnections() {
        return maxConnections;
    }
    @JsonProperty
    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public EntityManagerFactory build(Environment environment) {
        try {
            final EntityManagerFactory emf = Persistence.createEntityManagerFactory(getUrl());
            environment.lifecycle().manage(new Managed() {
                @Override
                public void start() {

                }

                @Override
                public void stop() {
                    if(emf.isOpen()) {
                        emf.close();
                    }
                }
            });
            return emf;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
