package com.easyTeach.server.domainLogic;

import com.easyTeach.common.entity.Class;
import com.easyTeach.common.entity.Resource;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.entity.User;
import com.easyTeach.common.entity.UserTestResult;
import com.easyTeach.common.network.Action;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.domainLogic.RoleResource.Role;

public final class Router {

	private Router() {

	}

	public static Response getResponse(Request toRoute) {
		Action action = toRoute.getAction();
		Resource resource = toRoute.getResource();
		Role role = toRoute.getRole();

		switch (role.toString()) {
		case "ADMIN":
			switch (resource.getName()) {
			case "Class":
				switch (action.getType().toString()) {
				case "CREATE":
					return ClassRules.addClass((Class) resource);
				case "READ":
                    switch(action.getAttribute()) {
                    case "all":
                        return ClassRules.getClasses();
                    case "students":
                        return ClassRules.getStudents((Class) resource);
                    default:
                        return ClassRules.getClass((Class) resource);
                    }
				case "UPDATE":
                    return ClassRules.updateClass((Class) resource);
				case "DELETE":
				    return ClassRules.deleteClass((Class) resource);
                }
				
			case "User":
			    switch (action.getType().toString()) {
                case "READ":
                    switch(action.getAttribute()) {
                    case "students":
                        return UserRules.getStudents();
                    case "teachers":
                        return UserRules.getTeachers();
                    case "admins":
                        return UserRules.getAdmins();
                    case "classes":
                        return UserRules.getClasses((User) resource);
                    default:
                        return UserRules.getUser((User) resource);
                    }
			    }
			    
			case "ClassUserRelationSet":
                switch (action.getType().toString()) {
                case "CREATE":
                    return ClassUserRelationRules.addRelations((ResourceSet) resource);
                case "UPDATE":
                    switch(action.getAttribute()) {
                    case "class":
                        return ClassUserRelationRules.updateRelationsByClass((ResourceSet) resource);
                    case "user":
                        return ClassUserRelationRules.updateRelationsByUser((ResourceSet) resource);
                    }
                }
			   
			case "UserTestResult":
			    switch (action.getType().toString()) {
			    case "CREATE":
			        return UserTestResultRules.addUserTestResult((UserTestResult) resource);
			    }
			    
			case "":
                switch (action.getType().toString()) {
                case "CREATE":
//                    return ;
                }
                
			}
		default: 
		    return new Response(ResponseStatus.SUCCESS);
		}

	}
		
}
