package il.tr.server.resource;

import com.codahale.metrics.annotation.Timed;
import il.tr.server.model.Example;
import il.tr.server.model.User;
import io.dropwizard.auth.Auth;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/example")
@Produces(MediaType.APPLICATION_JSON)
@Api(value="/example", description="Operations on the Example object")
public class ExampleResource {

    final static Logger logger = LoggerFactory.getLogger(ExampleResource.class);

    public ExampleResource() {

    }

    @GET
    @Timed
    @ApiOperation(value="Return a simple example", notes="some day this will do more...")
    @ApiResponses(value={@ApiResponse(code=400, message="Invalid ID"),
                         @ApiResponse(code = 200,message = "secret")})
    public Example getExample(@QueryParam("name") Optional<String> name) {
        logger.debug("calling GET /example resource");
        Example example = new Example();
        example.setName(name.orElse("default name"));
        return example;
    }

    @GET
    @Path("/secret")
    @Produces({"application/json"})
    @ApiOperation(
            value = "Secret",
            notes = "Returns secret",
            authorizations = {@Authorization("basic"), @Authorization("oauth2")}
    )
    @ApiResponses({@ApiResponse(code = 401, message = "Please enter basic credentials or use oauth2 authentication"),
                   @ApiResponse(code = 200, message = "secret")})
    public Example secret(@ApiParam(hidden = true) @Auth User user) {
        logger.debug("calling GET /example/secret resource");
        Example example = new Example();
        example.setExample(String.format("Hi %s! It's a secret message...", user.getName()));
        return example;
    }
}
