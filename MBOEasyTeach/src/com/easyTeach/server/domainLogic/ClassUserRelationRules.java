package com.easyTeach.server.domainLogic;

import java.util.HashSet;

import com.easyTeach.common.entity.ClassUserRelation;
import com.easyTeach.common.entity.Resource;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.databaseWrapper.ClassUserRelationWrapper;

public final class ClassUserRelationRules {

    private ClassUserRelationRules() {
    }

    /**
     * TODO
     * 
     * @param relations
     * @return
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
     * TODO
     * 
     * @param relations
     * @return
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
     * TODO
     * 
     * Synchronizes the FML
     * 
     * @param databaseSet
     *            The HashSet of all the ClassUserRelations with a specific
     *            userNo or classNo
     * @param relations
     *            The ResourceSet passed to the Domain Logic by the Presenter
     */
    private static void sync(HashSet<ClassUserRelation> databaseSet,
            ResourceSet relations) {
        // Insert new relations to the DB
        for (Resource resource : relations) {
            if (!databaseSet.contains(resource)) {
                ClassUserRelation cur = (ClassUserRelation) resource;
                ClassUserRelationWrapper.insertIntoClassUserRelation(cur);
            }
        }

        // Remove deleted relations from the DB
        for (ClassUserRelation cur : databaseSet) {
            if (!relations.contains(cur)) {
                ClassUserRelationWrapper.deleteClassUserRelationRow(
                        cur.getClassNo(), cur.getUserNo());
            }
        }
    }

    /**
     * TODO
     * 
     * 
     */
    public static Response addRelationsByUser(ResourceSet relations) {
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

        return new Response(ResponseStatus.FAIULRE,
                "Failed to insert all relations");
    }

}
