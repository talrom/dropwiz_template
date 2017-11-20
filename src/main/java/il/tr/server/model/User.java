package il.tr.server.model;

import java.security.Principal;

public class User implements Principal {
    private String name;

    public User(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

}