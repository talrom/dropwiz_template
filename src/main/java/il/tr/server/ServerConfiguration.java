package il.tr.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

public class ServerConfiguration extends Configuration {

    @NotEmpty
    private String uiFolder;

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

    @JsonProperty
    public String getUiFolder() {
        return uiFolder;
    }

    @JsonProperty
    public void setUiFolder(String uiFolder) {
        this.uiFolder = uiFolder;
    }

}
