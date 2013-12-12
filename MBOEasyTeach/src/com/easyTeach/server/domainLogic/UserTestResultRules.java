package com.easyTeach.server.domainLogic;

import com.easyTeach.common.entity.User;
import com.easyTeach.common.entity.UserTestResult;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.databaseWrapper.UserTestResultWrapper;

/**
 * Class used for manipulating with {@link UserTestResult} entities. It contains
 * the logic for adding a new row to the UserTestResult table in the DB using
 * the {@link UserTestResultWrapper}. The constructor is private as there should
 * never be created an instance of the UserTestResult class itself.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 11. December, 2013
 */

public class UserTestResultRules {

    private UserTestResultRules() {
    }

    /**
     * Adds a {@link UserTestResult} to the database.
     * 
     * @param utr
     *            the UserTestResult that should be added.
     * @return a Response object with a success status if the UserTestResult was
     *         added. Otherwise failure.
     */
    public static Response addUserTestResult(UserTestResult utr) {
        if (UserTestResultWrapper.insertIntoUserTestResult(utr)) {
            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE, "Failed to insert user");
    }

}
