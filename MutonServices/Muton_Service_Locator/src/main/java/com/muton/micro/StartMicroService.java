package com.muton.micro;

import com.muton.micro.resources.MicroServiceConfiguration;
import com.muton.micro.resources.ServiceLocatorResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 * Created by rohanw on 8/3/2015.
 */
public class StartMicroService extends Application<MicroServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new StartMicroService().run(args);
    }

    @Override
    public String getName() {
        return "micro-services";
    }

    @Override
    public void initialize(Bootstrap<MicroServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<MicroServiceConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(MicroServiceConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(MicroServiceConfiguration configuration,
                    Environment environment) {
        final ServiceLocatorResource serviceLocator = new ServiceLocatorResource();
        environment.jersey().register(serviceLocator);
    }
}
