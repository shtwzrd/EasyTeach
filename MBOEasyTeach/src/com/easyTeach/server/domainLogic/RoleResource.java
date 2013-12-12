package com.easyTeach.server.domainLogic;

import com.easyTeach.common.entity.Resource;

public class RoleResource implements Resource {

	private static final long serialVersionUID = 4089376751376304092L;
	private Role role;
	
	public RoleResource(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public enum Role {
		STUDENT, TEACHER, ADMIN
	}

	@Override
	public String getName() {
		return "RoleResource";
	}
}
