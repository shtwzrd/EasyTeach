package com.easyTeach.server.network;

import org.restlet.resource.ServerResource;
import com.easyTeach.common.network.UserResource;
import com.easyTeach.common.dl.User;

public class UserServerResource
    extends ServerResource
    implements UserResource {

    private User user;

    public UserServerResource() {
        this.user = new User("Tonni", "The Tiger", "studster@sheephooker.dk",
                "password");
    }

    public User representation() {
        return this.user;
    }

    public void store(User user) {

    }

    public void remove() {

    }

}
