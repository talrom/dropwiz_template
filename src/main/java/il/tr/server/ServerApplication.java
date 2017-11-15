package il.tr.server;

import il.tr.server.resource.ExampleResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class ServerApplication extends Application<ServerConfiguration> {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            args = new String[2];
            args[0] = "server";
            args[1] = "src/main/resources/server-config/server-config.yaml";
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

        ExampleResource exampleResource = new ExampleResource();
        environment.jersey().register(exampleResource);


    }
}
