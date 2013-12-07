package com.easyTeach.common.network;

import java.io.Serializable;

import com.easyTeach.common.network.resource.Resource;

public final class Request implements Serializable {

	private static final long serialVersionUID = -5146474164969036624L;
	private Action action;
	private Resource resource;
	private String user;
	private String password;

	public Request(String user, String password,
			Action action) {
		this.action = action;
		this.user = user;
		this.password = password;
	}
	public Request(String user, String password,
			Action action, Resource resource) {
		this.action = action;
		this.resource = resource;
		this.user = user;
		this.password = password;
	}

	public Action getAction() {
		return this.action;
	}

	public Resource getResource() {
		return this.resource;
	}

	public String getUser() {
		return this.user;
	}

	public String getPassword() {
		return this.password;
	}

}
