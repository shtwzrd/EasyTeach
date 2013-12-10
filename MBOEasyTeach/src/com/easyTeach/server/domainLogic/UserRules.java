package com.easyTeach.server.domainLogic;

import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.entity.User;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.databaseWrapper.UserWrapper;

public final class UserRules {

    private UserRules() {
    }
    
//    public static Response getUser(Resource resource) {
//        
//    }
    
    /**
     * 
     * @return
     */
    public static Response getUsers() {
        ResourceSet databaseUsers = new ResourceSet();

        for (User user : UserWrapper.getUserRows()) {
            databaseUsers.add(user);
        }

        return new Response(ResponseStatus.SUCCESS, databaseUsers);
    }
    
}
