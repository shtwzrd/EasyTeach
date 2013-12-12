package com.easyTeach.server.domainLogic;

import com.easyTeach.common.entity.Class;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.entity.User;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.databaseWrapper.ClassWrapper;
import com.easyTeach.server.databaseWrapper.UserWrapper;

/**
 * Class used for manipulating with {@link User} entities. It contains, among
 * other things, the logic for calling the CRUD procedures from the
 * {@link UserWrapper}. The constructor is private as there should never be
 * created an instance of the UserRules class itself.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 10. December, 2013
 */

public final class UserRules {

    private UserRules() {
    }

    /**
     * TODO Create a procedure for getClassesWithUserNo Method used for
     * retrieving all classes that a single user is associated to.
     * 
     * @param user
     *            entity containing the userNo of the user one is finding
     *            classes for.
     * @return a Response object with a success status and all the classes
     *         associated to a specific user.
     */
    public static Response getClasses(User user) {
        ResourceSet classes = new ResourceSet();

        for (Class classEntity : ClassWrapper.getClassesWithUserNo(user
                .getUserNo())) {
            classes.add(classEntity);
        }

        return new Response(ResponseStatus.SUCCESS, classes);
    }

    /**
     * Method used for retrieving a single {@link User} entitiy with a given
     * userNo.
     * 
     * @param user
     *            the user entity containing the userNo one is trying to
     *            retrieve
     * @return if successful, a Response object with a success status and
     *         specific user from the the DB. Otherwise, a failure response.
     */
    public static Response getUser(User user) {
        User newUser = UserWrapper.getUserRowWithUserNo(user.getUserNo());

        if (newUser != null) {
            return new Response(ResponseStatus.SUCCESS, newUser);
        }

        return new Response(ResponseStatus.FAILURE);
    }

    /**
     * Retrieves and returns a list with all the {@link User}s (Students) from
     * the DB.
     * 
     * @return if successful, a Response object with a success status and a list
     *         of all students in the DB.
     * @see Response
     */
    public static Response getStudents() {
        ResourceSet databaseStudents = new ResourceSet();

        for (User user : UserWrapper.getUserRows()) {
            if (user.getUserType().equals("Student")) {
                databaseStudents.add(user);
            }
        }

        return new Response(ResponseStatus.SUCCESS, databaseStudents);
    }

    /**
     * Retrieves and returns a list with all the {@link User}s (Teachers) from
     * the DB.
     * 
     * @return if successful, a Response object with a success status and a list
     *         of all teachers in the DB. Otherwise, a failure response.
     * @see Response
     */
    public static Response getTeachers() {
        ResourceSet databaseTeachers = new ResourceSet();

        for (User user : UserWrapper.getUserRows()) {
            if (user.getUserType().equals("Teacher")) {
                databaseTeachers.add(user);
            }
        }

        return new Response(ResponseStatus.SUCCESS, databaseTeachers);
    }

    /**
     * Retrieves and returns a list with all the {@link User}s (Admins) from the
     * DB.
     * 
     * @return if successful, a Response object with a success status and a list
     *         of all admins in the DB. Otherwise, a failure response.
     * @see Response
     */
    public static Response getAdmins() {
        ResourceSet databaseAdmins = new ResourceSet();

        for (User user : UserWrapper.getUserRows()) {
            if (user.getUserType().equals("Admin")) {
                databaseAdmins.add(user);
            }
        }

        return new Response(ResponseStatus.SUCCESS, databaseAdmins);
    }

    /**
     * Adds a {@link User} to the database.
     * 
     * @param user
     *            the user that should be added.
     * @return a Response object with a success status if the User was added.
     *         Otherwise false.
     */
    public static Response addUser(User user) {
        if (UserWrapper.insertIntoUser(user)) {
            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE, "Failed to insert user");
    }

    /**
     * Updates a {@link Class} row in the database.
     * 
     * @param classEntity
     *            the class that should be updated.
     * @return a Response object with a success status if the Class was updated.
     *         Otherwise false.
     */
    public static Response updateUser(User user) {
        if (UserWrapper.updateUserRow(user)) {
            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE, "Failed to update user");
    }

    /**
     * Deletes a {@link User} row in the database.
     * 
     * @param user
     *            the user that should be deleted.
     * @return a Response object with a success status if the User was deleted.
     *         Otherwise false.
     */
    public static Response deleteUser(User user) {
        if (UserWrapper.deleteUserRow(user.getUserNo())) {
            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE, "Failed to delete user");
    }

}
