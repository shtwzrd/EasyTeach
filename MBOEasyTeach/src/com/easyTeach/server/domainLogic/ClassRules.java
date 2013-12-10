package com.easyTeach.server.domainLogic;

import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.databaseWrapper.ClassWrapper;
import com.easyTeach.server.databaseWrapper.UserWrapper;
import com.easyTeach.common.entity.Class;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.entity.User;

/**
 * Class used for manipulating with {@link Class} entities. It contains, among
 * other things, the logic for calling the CRUD procedures from the
 * {@link ClassWrapper}. The constructor is private as there should never be
 * created an instance of the ClassRules class itself.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 10. December, 2013
 */

public final class ClassRules {

    private ClassRules() {
    }

    /**
     * Method used for retrieving a single {@link Class} entitiy with a given
     * classNo.
     * 
     * @param classEntity
     *            the class entity containing the classNo one is trying to
     *            retrieve.
     * @return if successful, a Response object with a success status and
     *         specific class from the the DB. Otherwise, a failure response.
     */
    public static Response getClass(Class classEntity) {
        Class newClassEntity = ClassWrapper.getClassRowByClassNo(classEntity
                .getClassNo());
        if (newClassEntity != null) {
            return new Response(ResponseStatus.SUCCESS, newClassEntity);
        }

        return new Response(ResponseStatus.FAILURE, "Failed to find class");
    }

    /**
     * Retrieves and returns a list with all the {@link Class}es from the DB.
     * 
     * @return if successful, a Response object with a success status and a list
     *         of all classes in the DB.
     * @see Response
     */
    public static Response getClasses() {
        ResourceSet databaseClasses = new ResourceSet();

        for (Class classEntity : ClassWrapper.getClassRows()) {
            databaseClasses.add(classEntity);
        }

        return new Response(ResponseStatus.SUCCESS, databaseClasses);
    }

    /**
     * Method used for retrieving all students that a single class is associated
     * to.
     * 
     * @param classEntity
     *            entity containing the classNo of the class one is finding
     *            students for.
     * @return a Response object with a success status and all the students
     *         associated to a specific class.
     */
    public static Response getStudents(Class classEntity) {
        ResourceSet students = new ResourceSet();

        for (User user : UserWrapper.getUserRowsWithClassNo(classEntity
                .getClassNo())) {
            if (user.getUserType().equals("Student")) {
                students.add(user);
            }
        }

        return new Response(ResponseStatus.SUCCESS, students);
    }

    /**
     * Updates a {@link Class} row in the database.
     * 
     * @param classEntity the class that should be updated.
     * @return a Response object with a success status if the Class was updated.
     *         Otherwise false.
     */
    public static Response updateClass(Class classEntity) {
        if (ClassWrapper.updateClassRow(classEntity)) {
            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE, "Failed to update class");
    }

    /**
     * Deletes a {@link Class} row in the database.
     * 
     * @param classEntity the class that should be deleted.
     * @return a Response object with a success status if the Class was deleted.
     *         Otherwise false.
     */
    public static Response deleteClass(Class classEntity) {
        if (ClassWrapper.deleteClassRow(classEntity.getClassNo())) {
            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE, "Failed to delete class");
    }

    /**
     * Adds a {@link Class} row to the database.
     * 
     * @param classEntity the class that should be added.
     * @return a Response object with a success status if the Class was added.
     *         Otherwise false.
     */
    public static Response addClass(Class classEntity) {
        if (ClassWrapper.insertIntoClass(classEntity)) {
            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE, "Failed to insert class");
    }

}
