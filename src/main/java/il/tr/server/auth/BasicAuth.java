package il.tr.server.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import il.tr.server.impl.ServerConfiguration;
import il.tr.server.model.User;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

public class BasicAuth implements Authenticator<BasicCredentials, User> {

    private String secret;

    public BasicAuth(ServerConfiguration conf) {
        this.secret = conf.getSecret();
    }

    @Override
    public Optional<User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        if (secret.equals(basicCredentials.getPassword())) {
            return Optional.of(new User(basicCredentials.getUsername()));
        }
        throw new AuthenticationException("Bad Credentials!");
    }
}
