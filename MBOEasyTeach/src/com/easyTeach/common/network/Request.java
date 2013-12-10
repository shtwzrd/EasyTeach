package com.easyTeach.common.network;

import java.io.Serializable;

import com.easyTeach.common.entity.Resource;
import com.easyTeach.server.domainLogic.RoleResource.Role;

/**
 * A Request is a message sent from Client to Server.
 * <br>
 * <p>
 * It is an object encapsulating all the information required to describe a task
 * to perform on the server. It consists of an {@link Action} - a verb
 * describing what to do, a {@link Resource} - an object encapsulating the state
 * of the noun on which the Action is to be applied, and a {@link Session}
 * object, which will be used for Authenticating and Authorizing the Request
 * prior to honoring it.
 * </p>
 * 
 * @see Authenticator
 * @see Router
 * @see EasyTeachServer
 * 
 * @author Brandon Lucas
 * @version 1.0
 * @date 6. December, 2013
 */
public final class Request implements Serializable {

	private static final long serialVersionUID = -5146474164969036624L;
	private Action action;
	private Resource resource;
	private Session session;
	private Role role;

	/**
	 * Constructor for an "empty" Request, not requiring a "noun." A CLOSE
	 * Request typically does not require a Resource, for example.
	 * 
	 * @param session
	 *            The session object containing the credentials of the currently
	 *            logged-in user.
	 * @param action
	 *            Action object describing what the client would like the server
	 *            to do.
	 */
	public Request(Session session, Action action) {
		this.action = action;
		this.session = session;
	}

	/**
	 * Constructor for a typical, full-bodied Request.
	 * 
	 * @param session
	 *            The session object containing the credentials of the currently
	 *            logged-in user.
	 * @param action
	 *            Action object describing what the client would like the server
	 *            to do.
	 * @param resource
	 *            Object describing what sort of entity the action should be
	 *            performed upon, and any necessary state required to do so.
	 */
	public Request(Session session, Action action, Resource resource) {
		this.action = action;
		this.resource = resource;
		this.session = session;
	}

	/**
	 * Constructor used by the Authenticator class.
	 * 
	 * <p>
	 * Once the Authenticator has validated a user, it strips the Session object
	 * out of the Request, as it has already been verified, and instead it
	 * passes a Role object so that the Router knows what to do with it. <b>
	 * Don't send these kinds of Requests to the Server - it will reject them.
	 * </b>
	 * </p>
	 * 
	 * @param action
	 *            Action object describing what the client would like the server
	 *            to do.
	 * @param resource
	 *            Object describing what sort of entity the action should be
	 *            performed upon, and any necessary state required to do so.
	 * @param role
	 *            The level of privilege the Authenticator assigned the Request.
	 */

	public Request(Action action, Resource resource, Role role) {
		this.action = action;
		this.resource = resource;
		this.role = role;
	}

	public Action getAction() {
		return this.action;
	}

	public Resource getResource() {
		return this.resource;
	}

	public Session getSession() {
		return this.session;
	}

	public Role getRole() {
		return this.role;
	}

}
