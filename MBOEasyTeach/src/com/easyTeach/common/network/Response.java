package com.easyTeach.common.network;

import java.io.Serializable;

public final class Response implements Serializable {

	private static final long serialVersionUID = 8697470653282332307L;
	private Resource payload;
	private ResponseStatus status;
	
	public Response(Resource payload, ResponseStatus status) {
		this.payload = payload;
		this.status = status;
	}
	
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