package com.easyTeach.common.network;

import java.io.Serializable;
import java.util.List;

public final class Response implements Serializable {

	private static final long serialVersionUID = -8333015697444572625L;
	private List<String> payload;
	private boolean closeResponse;
	
	public Response(List<String> payload) {
		this.payload = payload;
		this.closeResponse = false;
	}
	
	public Response(boolean closing) {
		this.closeResponse = closing;
	}
	
	public List<String> getResponse() {
		return this.payload;
	}
	
	public boolean isClosing() {
		return closeResponse;
	}

}
