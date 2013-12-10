package com.easyTeach.server.domainLogic;

import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.databaseWrapper.ClassWrapper;
import com.easyTeach.server.databaseWrapper.UserWrapper;
import com.easyTeach.common.entity.Class;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.entity.User;

public final class ClassRules {

    private ClassRules() {
    }

    public static Response getClass(Class classEntity) {
        Class newClassEntity = ClassWrapper.getClassRowByClassNo(classEntity
                .getClassNo());
        if (newClassEntity != null) {
            return new Response(ResponseStatus.SUCCESS, newClassEntity);
        }

        return new Response(ResponseStatus.FAILURE, "Failed to find class");
    }

    public static Response getUsers(Class classEntity) {
        ResourceSet users = new ResourceSet();

        for (User user : UserWrapper.getUserRowsWithClassNo(classEntity
                .getClassNo())) {
            users.add(user);
        }

        return new Response(ResponseStatus.SUCCESS, users);
    }

    public static Response updateClass(Class classEntity) {
        if (ClassWrapper.updateClassRow(classEntity)) {
            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE, "Failed to update class");
    }

    public static Response deleteClass(Class classEntity) {
        if (ClassWrapper.deleteClassRow(classEntity.getClassNo())) {
            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE, "Failed to update class");
    }

    public static Response addClass(Class classEntity) {
        if (ClassWrapper.insertIntoClass(classEntity)) {
            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE, "Failed to insert class");
    }

}
