package com.easyTeach.server.network;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Context;
import org.restlet.routing.Router;

public class EasyTeachApplication extends Application {


    public EasyTeachApplication() {
        super();
    }

    public EasyTeachApplication(Context context) {
        super(context.createChildContext());
    }

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext().createChildContext());
        router.attach("/", RootServerResource.class);
        router.attach("/users/{user}", UserServerResource.class);

        return router;
    }
}
