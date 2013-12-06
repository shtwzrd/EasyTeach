package com.easyTeach.common.network.resource;


public class RoleResource implements Resource {
	
	private static final long serialVersionUID = -844956815181244552L;
	private Role role;
	
	public RoleResource(Role role) {
		this.role = role;
	}
	
	public Role getRole() {
		return this.role;
	}
	
	public enum Role {
		STUDENT, TEACHER, ADMIN;
	}

}
