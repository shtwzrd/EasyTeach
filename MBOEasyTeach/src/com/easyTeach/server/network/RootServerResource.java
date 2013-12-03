package com.easyTeach.server.network;

import org.restlet.resource.ServerResource;
import com.easyTeach.common.network.RootResource;

public class RootServerResource
    extends ServerResource
    implements RootResource {

    public String represent() {
        return "Welcome to EasyTeach. We love you!";
    }

}
