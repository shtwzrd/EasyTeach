package com.easyTeach.common.network;

import org.restlet.resource.*;
import com.easyTeach.common.dl.User;

/**
 * <p>
 * Wow. So REST. Such portable.
 * </p>
 * 
 * @author Brandon Lucas
 * @version 1.0
 * @date 2. December, 2013
 */
public interface UserResource {

    @Get
    public User representation();

    @Put
    public void store(User user);

    @Delete
    public void remove();

}
