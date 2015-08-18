package com.muton.micro;

import com.muton.micro.resources.AuthenticationResource;
import com.muton.micro.resources.AuthorizationResource;
import com.muton.micro.resources.MicroServiceConfiguration;
import com.muton.micro.resources.UserManagerResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.persistence.EntityManagerFactory;

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
        EntityManagerFactory emf = configuration.getDatabase().build(environment);
        final AuthenticationResource authentication = new AuthenticationResource(emf.createEntityManager());
        environment.jersey().register(authentication);
        final AuthorizationResource authorization = new AuthorizationResource(emf.createEntityManager());
        environment.jersey().register(authorization);
        final UserManagerResource userManager = new UserManagerResource(emf.createEntityManager());
        environment.jersey().register(userManager);
    }
}
