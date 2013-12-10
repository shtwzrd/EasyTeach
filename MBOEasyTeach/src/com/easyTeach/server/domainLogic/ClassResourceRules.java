package com.easyTeach.server.domainLogic;

import java.util.HashSet;

import com.easyTeach.common.entity.Class;
import com.easyTeach.common.entity.ClassUserRelation;
import com.easyTeach.common.entity.User;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.resource.ClassResource;
import com.easyTeach.common.network.resource.UserResource;
import com.easyTeach.server.databaseWrapper.ClassUserRelationWrapper;
import com.easyTeach.server.databaseWrapper.ClassWrapper;
import com.easyTeach.server.databaseWrapper.UserWrapper;

public class ClassResourceRules {

    private ClassResourceRules() {
    }

    public static Response addClass(ClassResource classResource) {
        Class classEntity = new Class();
        classEntity.setClassName(classResource.getClassName());
        classEntity.setYear(classResource.getYear());

        if (ClassWrapper.insertIntoClass(classEntity)) {
            // Get's the new class' classNo
            String classNo = ClassWrapper.getClassRowByClassName(
                    classEntity.getClassName()).getClassNo();
            for (UserResource student : classResource.getUsers()) {
                ClassUserRelation cur = new ClassUserRelation();
                cur.setClassNo(classNo);
                cur.setUserNo(student.getUserNo());
                ClassUserRelationWrapper.insertIntoClassUserRelation(cur);
            }

            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE);
    }

    public static Response updateClass(ClassResource classResource) {
        if (ClassWrapper.updateClassRow(convertToEntity(classResource))) {
            HashSet<User> userEntities = UserWrapper.getUserRows();
            for (ClassUserRelation cur : ClassUserRelationWrapper.getClassUserRelationRowsWithClassNo(classResource.getClassNo())) {
                for (UserResource user : classResource.getUsers()) {
                    if (cur.getUserNo() == user.getUserNo())
                            
                }
                if (classResource.getUsers().contains(cur.getUserNo()))
            }
            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE);
    }

    public static Response removeClass(ClassResource classResource) {
        if (ClassWrapper.deleteClassRow(classResource.getClassNo())) {
            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE);
    }

    public static Response getClass(ClassResource classResource) {
        Class classEntity = ClassWrapper.getClassRowByClassName(classResource
                .getClassName());

        if (classEntity != null) {
            HashSet<UserResource> users = new HashSet<UserResource>();
            HashSet<String> userNumbers = new HashSet<String>();
            HashSet<User> userEntities = UserWrapper.getUserRows();

            // Finds all the user numbers with a specific classNo and adds them
            // to the HashSet userNumbers.
            for (ClassUserRelation cur : ClassUserRelationWrapper
                    .getClassUserRelationRowsWithClassNo(classResource
                            .getClassNo())) {
                userNumbers.add(cur.getUserNo());
            }

            // Adds all users from the database with a certain class
            // FUCK IT USER SQL PROCEDURES
            for (User userEntity : userEntities) {
                if (userNumbers.contains(userEntity.getUserNo())) {
                    users.add(UserResourceRules.convertToResource(userEntity));
                }
            }

            ClassResource resource = new ClassResource(
                    classEntity.getClassName(), classEntity.getYear(), users);

            return new Response(ResponseStatus.SUCCESS, resource);
        }

        return new Response(ResponseStatus.FAILURE);
    }

    public static Response getClasses(ClassResource classResource) {
        Class classEntity = ClassWrapper.getClassRowByClassName(classResource
                .getClassName());

        if (classEntity != null) {
            return new Response(ResponseStatus.SUCCESS,
                    convertToResource(classEntity));
        }

        return new Response(ResponseStatus.FAILURE);
    }

    /**
     * Converts a Class entity into a ClassResource
     * 
     * @param classEntity
     *            instance of Class
     * @return the classResource the classEntity has been converted into
     * @see Class
     * @see ClassResource
     */
    public static ClassResource convertToResource(Class classEntity, HashSet<UserResource> users) {
        ClassResource classResource = new ClassResource(
                classEntity.getClassNo(), classEntity.getClassName(),
                classEntity.getYear(), users);

        return classResource;
    }

    /**
     * Converts a Resource into a Class entity
     * 
     * @param classResource
     *            instance of ClassResource
     * @return the classEntity the classResource has been converted into
     * @see Class
     * @see ClassResource
     */
    public static Class convertToEntity(ClassResource classResource) {
        Class classEntity = new Class();
        classEntity.setClassNo(classResource.getClassNo());
        classEntity.setClassName(classResource.getClassName());
        classEntity.setYear(classResource.getYear());

        return classEntity;
    }

}
