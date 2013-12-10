package com.easyTeach.server.domainLogic;

import com.easyTeach.common.entity.User;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.resource.UserResource;
import com.easyTeach.server.databaseWrapper.UserWrapper;

/**
 * <p>
 * The UserResourceRules class returns all needed domain logic (or business
 * logic) for the user to the @Response class.
 * </p>
 * 
 * @author Tonni Hyldgaard
 * @version 0.1
 * @see User
 * @see UserResource
 * @see UserWrapper
 * @see Response
 * @data 9. December, 2013.
 */
public class UserResourceRules {

	private UserResourceRules() {
	}

	public static Response addUser(UserResource user) {
		User userEntity = new User();
		userEntity.setEmail(user.getEmail());
		userEntity.setUserType(user.getUserType());
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		
		if (userEntity != null 
				&& userEntity.equals(user.getEmail()) 
				&& userEntity.equals(user.getUserType())
				&& userEntity.equals(user.getFirstName())
				&& userEntity.equals(user)) {
			
		}
	}

	public static Response getUser(UserResource user) {
		return null;
	}

	public static Response getAllUsers(UserResource user) {
		return null;
	}

	public static Response getEnrolledClasses(UserResource user) {
		return null;
	}

	public static Response updatePassword(UserResource user) {
		return null;
	}

	public static Response updateUser(UserResource user) {
		User userEntity = new User();
		userEntity.setEmail(user.getEmail());
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		
		if (userEntity != null && !userEntity.equals(user)) {
			return new Response(ResponseStatus.SUCCESS, new UserResource();
		}
	}

	public static Response addCompletedQuestion(UserResource user) {
		return null;
	}

	public static Response removeUser(UserResource user) {
		if (UserWrapper.deleteuserRow(user.getUserNo())) {
			return new Response(ResponseStatus.SUCCESS);
		} else {
			return new Response(ResponseStatus.FAILURE);
		}
	}
	
	public static UserResource convertToResource(User user) {
		UserResource userResource = new UserResource();
	}

}
