package il.tr.server.resource;

import com.codahale.metrics.annotation.Timed;
import il.tr.server.model.Example;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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

    public ExampleResource() {

    }

    @GET
    @Timed
    @ApiOperation(value="Return a simple example", notes="some day this will do more, it believes in a growth mentality.")
    @ApiResponses(value={@ApiResponse(code=400, message="Invalid ID"),})
    public Example getExample(@QueryParam("name") Optional<String> name) {
        Example example = new Example();
        example.setName(name.orElse("default name"));
        return example;
    }
}
