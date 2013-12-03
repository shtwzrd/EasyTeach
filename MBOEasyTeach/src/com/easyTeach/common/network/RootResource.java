package com.easyTeach.common.network;

import org.restlet.resource.Get;

public interface RootResource {

    @Get
    public String represent();

}
