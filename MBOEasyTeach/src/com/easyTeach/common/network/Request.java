/**
 * <p>
 *  A Request is an object encapsulating all the information
 *  required to describe a task to perform on the server. It
 *  consists of an @see Action - a verb describing what to do,
 *  a @see Resource - an object encapsulating the state of the
 *  noun on which the Action is to be applied, and a @see Session
 *  object, which will be used for Authenticating and Authorizing
 *  the Request prior to honoring it.
 * </p>
 *
 * @author Brandon Lucas
 * @version 1.0
 * @date 6. December, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters/setters).
 */
package com.easyTeach.common.network;

import java.io.Serializable;

import com.easyTeach.common.network.resource.Resource;

public final class Request implements Serializable {

	private static final long serialVersionUID = -5146474164969036624L;
	private Action action;
	private Resource resource;
	private Session session;

	/**
	 * <p>
	 * Constructor for an "empty" Request, not requiring a "noun."
	 * A CLOSE Request typically does not require a Resource, for
	 * example.
	 * </p>
	 * @param session The session object containing the credentials
	 * of the currently logged-in user.
	 * @param action Action object describing what the client would
	 * like the server to do.
	 */
	public Request(Session session, Action action) {
		this.action = action;
		this.session = session;
	}
	
	/**
	 * Constructor for a typical, full-bodied Request.
	 * 
	 * @param session The session object containing the credentials
	 * of the currently logged-in user.
	 * @param action Action object describing what the client would
	 * like the server to do.
	 * @param resource Object describing what sort of entity the 
	 * action should be performed upon, and any necessary state
	 * required to do so.
	 */
	public Request(Session session, Action action,
			Resource resource) {
		this.action = action;
		this.resource = resource;
		this.session = session;
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

}
