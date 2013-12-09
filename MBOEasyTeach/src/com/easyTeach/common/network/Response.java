package com.easyTeach.common.network;

import java.io.Serializable;
import com.easyTeach.common.network.resource.Resource;

/**
 * A Response is a message sent from Server to Client. <br>
 * <p>
 * It is an object containing the result of a Request, consisting of a
 * ResponseStatus and optionally a Resource sharing the change in Resource state
 * on the Server as a result of honoring the Request.
 * </p>
 * 
 * @author Brandon Lucas
 * @version 1.0
 * @date 6. December, 2013
 */
public final class Response implements Serializable {

	private static final long serialVersionUID = 8697470653282332307L;
	private Resource payload;
	private ResponseStatus status;

	/**
	 * Full-bodied Response, containing a Resource. this is usually the type of
	 * Response issued for a successful READ Request.
	 * 
	 * @param status
	 *            An enumerated type, denoting the kind of Response; whether it
	 *            was a Success or a Failure, for example.
	 * @param resource
	 *            An object representing the state change caused by the Request.
	 */
	public Response(ResponseStatus status, Resource resource) {
		this.payload = resource;
		this.status = status;
	}

	/**
	 * A simple Reponse with only a status - usually the type of Response issued
	 * for CREATE, UPDATE or DELETE Requests.
	 * 
	 * @param status
	 *            An enumerated type, denoting the kind of Response; whether it
	 *            was a Success or a Failure, for example.
	 */
	public Response(ResponseStatus status) {
		this.status = status;
	}

	public Resource getResponse() {
		return this.payload;
	}

	public ResponseStatus getStatus() {
		return this.status;
	}

	public enum ResponseStatus {
		SUCCESS, FAILURE, CLOSE;
	}

}