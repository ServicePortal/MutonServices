package com.muton.micro.jdbi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by rohanw on 7/22/2015.
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

    public BoneCP build(Environment environment) {
        try {
            Class.forName(getDriver());
            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl(getUrl());
            config.setUsername(getUsername());
            config.setPassword(getPassword());
            config.setMinConnectionsPerPartition(getMinConnections()); //if you say 5 here, there will be 10 connection available   config.setMaxConnectionsPerPartition(10);
            config.setMaxConnectionsPerPartition(getMaxConnections());
            config.setPartitionCount(1); //2*5 = 10 connection will be available
            config.setLazyInit(true); //depends on the application usage you should chose lazy or not
            config.setAcquireIncrement(2);
            config.setIdleConnectionTestPeriodInMinutes(10);
            config.setIdleMaxAgeInMinutes(10);
            final BoneCP pool = new BoneCP(config);
            environment.lifecycle().manage(new Managed() {
                @Override
                public void start() {

                }

                @Override
                public void stop() {
                    pool.close();
                }
            });
            return pool;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
