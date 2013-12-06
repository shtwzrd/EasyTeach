package com.easyTeach.common.network;

import java.io.Serializable;

import com.easyTeach.common.network.resource.Resource;

public final class Response implements Serializable {

	private static final long serialVersionUID = 8697470653282332307L;
	private Resource payload;
	private ResponseStatus status;
	
	public Response(ResponseStatus status, Resource resource) {
		this.payload = resource;
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