package com.muton.micro.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.muton.micro.jdbi.JDBCFactory;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by rohanw on 8/3/2015.
 */
public class MicroServiceConfiguration extends Configuration {
    @Valid
    @NotNull
    public SwaggerBundleConfiguration swaggerBundleConfiguration;
    @Valid
    @NotNull
    private JDBCFactory database = new JDBCFactory();

    @JsonProperty("documentation")
    public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
        return swaggerBundleConfiguration;
    }
    @JsonProperty("documentation")
    public void setSwaggerBundleConfiguration(SwaggerBundleConfiguration swaggerBundleConfiguration) {
        this.swaggerBundleConfiguration = swaggerBundleConfiguration;
    }
    @JsonProperty("database")
    public JDBCFactory getDatabase() {
        return database;
    }
    @JsonProperty("database")
    public void setDatabase(JDBCFactory database) {
        this.database = database;
    }
}
