package com.easyTeach.server.domainLogic;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.easyTeach.common.entity.User;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.resource.RoleResource;
import com.easyTeach.common.network.resource.RoleResource.Role;
import com.easyTeach.server.databaseWrapper.UserWrapper;

/**
 * The Authenticator class is a class that authenticates and authorizes
 * different operations sent to the server via {@link Request}s
 * 
 * @author Oliver Nielsen, Brandon Lucas
 * @version 1.0
 * @date 06. December, 2013
 */
public final class Authenticator {

	/**
	 * Private constructor for a class that is only referenced statically.
	 */
	private Authenticator() {
	}

	/**
	 * Authenticates and Authorizes a user requesting interaction with the
	 * Server.
	 * 
	 * @param request
	 *            Request containing the session token for the user of interest.
	 * @return Returns a Response object with a Success or Failure status, and
	 *         the level of authorization given to the user provided
	 *         authentication was successful.
	 */
	public static Response authenticateUser(Request request) {
		StrongPasswordEncryptor pEncrypt = new StrongPasswordEncryptor();

		String username = request.getSession().getUsername();
		String password = request.getSession().getPassword();

		User user = UserWrapper.getUserRowWithEmail(username);

		if (user != null
				&& pEncrypt.checkPassword(password, user.getPassword())) {
			Role role = Role.STUDENT;
			switch (user.getUserType()) {
			case "Teacher":
				role = Role.TEACHER;
				break;
			case "Admin":
				role = Role.ADMIN;
				break;
			}

			// Special case: If the Action was simply to "authenticate", the
			// client has
			// succeeded. There is no need to further route the Request.
			if (request.getAction().getAttribute().equals("authenticate")) {
				return new Response(ResponseStatus.SUCCESS, new RoleResource(
						role));
				// Otherwise, pass the Request to the Router to find out what to
				// do with it.
			} else {
				Request toRouter = new Request(request.getAction(),
						request.getResource(), role);
				return Router.getResponse(toRouter);
			}
		} else { // We couldn't authenticate that user!
			return new Response(ResponseStatus.FAILURE);
		}
	}
}
