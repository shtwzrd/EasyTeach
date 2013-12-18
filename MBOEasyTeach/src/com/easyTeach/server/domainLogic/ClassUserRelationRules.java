package com.easyTeach.server.domainLogic;

import java.util.HashSet;

import com.easyTeach.common.entity.ClassUserRelation;
import com.easyTeach.common.entity.Resource;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.databaseWrapper.ClassUserRelationWrapper;

/**
 * Class used for manipulating with {@link ClassUserRelation} entities. It
 * contains, the logic for updating and adding ClassUserRelation using the
 * {@link ClassUserRelationWrapper}. The constructor is private as there should
 * never be created an instance of the ClassUserRelationRules class itself.
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 10. December, 2013
 */

public final class ClassUserRelationRules {

    private ClassUserRelationRules() {
    }

    /**
     * Updates {@link ClassUserRelation} rows in the database by a specific
     * class.
     * 
     * @param relations
     *            the HashSet of ClassUserRelations that should be synchronized
     *            with the database.
     * @return a Response object with a success status if the ClassUserRelation
     *         was updated.
     */
    public static Response updateRelationsByClass(ResourceSet relations) {
        // Finds out what classNo we are working with
        ClassUserRelation entity = (ClassUserRelation) relations.toArray()[0];
        String classNo = entity.getClassNo();

        // Gets all the ClassUserRelations with the specific classNo
        HashSet<ClassUserRelation> databaseSet = ClassUserRelationWrapper
                .getClassUserRelationRowsWithClassNo(classNo);

        sync(databaseSet, relations);

        return new Response(ResponseStatus.SUCCESS);
    }

    /**
     * Updates {@link ClassUserRelation} rows in the database by a specific
     * user.
     * 
     * @param relations
     *            HashSet of ClassUserRelations that should be synchronized with
     *            the database.
     * @return a Response object with a success status if the ClassUserRelation
     *         was updated.
     */
    public static Response updateRelationsByUser(ResourceSet relations) {
        // Finds out what userNo we are working with
        ClassUserRelation entity = (ClassUserRelation) relations.toArray()[0];
        String userNo = entity.getUserNo();

        // Gets all the ClassUserRelations with the specific classNo
        HashSet<ClassUserRelation> databaseSet = ClassUserRelationWrapper
                .getClassUserRelationRowsWithUserNo(userNo);

        sync(databaseSet, relations);

        return new Response(ResponseStatus.SUCCESS);
    }

    /**
     * Synchronizes the {@link ClassUserRelation} database-rows for a specific
     * user or class with a given set of ClassUserRelations.
     * 
     * @param databaseSet
     *            HashSet of all the ClassUserRelations with a specific userNo
     *            or classNo
     * @param relations
     *            The ResourceSet passed to the Domain Logic by the Presenter
     */
    private static void sync(HashSet<ClassUserRelation> databaseSet,
            ResourceSet relations) {
        // Insert new relations to the DB
    	System.out.println("In DB: " + databaseSet.toString());
        for (Resource resource : relations) {
            if (!databaseSet.contains(resource)) {
                ClassUserRelation cur = (ClassUserRelation) resource;
                ClassUserRelationWrapper.insertIntoClassUserRelation(cur);
            }
        }

        System.out.println("In set: " + relations.toString());
        // Remove deleted relations from the DB
        for (ClassUserRelation cur : databaseSet) {
            if (!relations.contains(cur)) {
                ClassUserRelationWrapper.deleteClassUserRelationRow(
                        cur.getClassNo(), cur.getUserNo());
            }
        }
    }

    /**
     * Adds a set of {@link ClassUserRelations} to the database.
     * 
     * @param relations
     *            HashSet of ClassUserRelations that should be added with the
     *            database.
     * @return a Response object with a success status if all
     *         {@link ClassUserRelations} were added. Otherwise false.
     */
    public static Response addRelations(ResourceSet relations) {
        boolean checker = true;

        for (Resource resource : relations) {
            if (!ClassUserRelationWrapper
                    .insertIntoClassUserRelation((ClassUserRelation) resource)) {
                checker = false;
            }
        }

        if (checker) {
            return new Response(ResponseStatus.SUCCESS);
        }

        return new Response(ResponseStatus.FAILURE,
                "Failed to insert all relations");
    }

}
