package il.tr.server.impl;

import il.tr.server.auth.BasicAuth;
import il.tr.server.model.User;
import il.tr.server.resource.ExampleResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ServerApplication extends Application<ServerConfiguration> {

    final static Logger logger = LoggerFactory.getLogger(ServerApplication.class);

    public static void main(String[] args) throws Exception {
        logger.debug("args size: " + args.length);

        if (args.length == 0) {
            List<String> arr = new ArrayList<>();
            arr.add("server");
            arr.add("src/main/resources/server-config/server-config.yaml");

            args = arr.stream().toArray(String[]::new);
        }

        new ServerApplication().run(args);
    }

    @Override
    public String getName() {
        return "Server Template with Swagger";
    }

    @Override
    public void initialize(Bootstrap<ServerConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<ServerConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(ServerConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });

    }

    public void run(ServerConfiguration serverConfiguration, Environment environment) throws Exception {
        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new BasicAuth(serverConfiguration))
                        .setRealm("SUPER SECRET STUFF")
                        .buildAuthFilter()));

        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));

        ExampleResource exampleResource = new ExampleResource();
        environment.jersey().register(exampleResource);
    }
}
